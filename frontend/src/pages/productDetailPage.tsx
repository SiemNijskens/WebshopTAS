import { useMutation, useQuery } from "@tanstack/react-query";
import { useParams } from "react-router";
import type { ProductBaseDTO, SelectedAttributes } from "../types/models";
import { API_URL } from "../App";
import { useState } from "react";
import '../styles/product.css'
import { createCart, setCart, useCartStore } from "../components/stores/cartStore";

const ProductDetailPage = () => {
    const { productId } = useParams<{ productId: string }>();
    const numericProductId = Number(productId);
    const { cart } = useCartStore();

    const { data: product, isLoading, error } = useQuery<ProductBaseDTO>({
        queryKey: ['product', numericProductId],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/products/${productId}`);
            if (!response.ok) {
                throw new Error("Failed to fetch product")
            }
            return response.json();
        },
    });

    const addToCartMutation = useMutation({
        mutationFn: async ({
            cartId,
            productId,
            amount,
        }: {
            cartId: number;
            productId: number;
            amount: number;
        }) => {
            const response = await fetch(`${API_URL}/shoppingcarts/${cartId}/items`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ productId, amount }),
            });
            if (!response.ok) {
                throw new Error("Failed to add product to cart");
            }
            return response.json();
        },
        onSuccess: (updatedCart) => {
            setCart(updatedCart);
            alert("item added to cart!");
        }
    })

    const allAttributes = product?.productVariants.flatMap(variants => variants.attributes);

    const attributeMap = new Map<string, Set<string>>();

    allAttributes?.forEach(attr => {
        const name = attr.attribute;
        const value = attr.value;

        if (!attributeMap.has(name)) {
            attributeMap.set(name, new Set());
        }
        attributeMap.get(name)!.add(value);
    })

    const [selectedAttributes, setSelectedAttributes] = useState<SelectedAttributes>({});
    const [quantity, setQuantity] = useState(1);

    const selectedVariant = product?.productVariants.find(variant =>
        variant.attributes.every(attr => selectedAttributes[attr.attribute] === attr.value)
    );

    const lowStock = selectedVariant && selectedVariant.stock === 1;

    const isOptionOutOfStock = (attributeName: string,  value: string) => {
        return !product?.productVariants.some(variant => {
            
            const matchesSelected = Object.entries(selectedAttributes).every(
                ([attr, val]) =>
                    attr === attributeName ||
                    variant.attributes.some(at => at.attribute === attr && at.value === val)
            );

            const matchesThisOption = variant.attributes.some(
                a => a.attribute === attributeName && a.value === value
            );

            return matchesSelected && matchesThisOption && variant.stock > 0;
        });
    };

    // const attributeByName = allAttributes?.reduce((acc, attr) => {
    //     if (!acc[attr.attribute]) {
    //         acc[attr.attribute] = new Set();
    //     }

    //     acc[attr.attribute].add(attr.value);
    //     return acc;
    // }, {} as Record<string, Set<string>>);

//    const attributeName = (allAttributes: ProductDTO) => {
//         const map = new Map<string, ProductDTO>();
 
//         allAttributes.attributes.forEach(variant => {
//             if (!map.has(variant.attribute)) {
//                 map.set(variant.attribute, variant);
//             }
//         });
//         // productVariants.forEach(variant => {
//         //     if (!map.has(variant.color)) {
//         //     map.set(variant.color, variant);
//         //     }
//         // });
//         return Array.from(map.values());
//     }


    // type AttributeMap = Record<string, string[]>;

    // const attributeMap: AttributeMap = {};

    // product?.productVariants.forEach(variant => {
    //     variant.attributes.forEach(attr => {
    //         if (!attributeMap[attr.attribute]) {
    //             attributeMap[attr.attribute] = [];
    //         }

    //         if (!attributeMap[attr.attribute].includes(attr.value)) {
    //             attributeMap[attr.attribute].push(attr.value);
    //         }
    //     });
    // });

    // const [selectedAttributes, setSelectedAttributes] = useState<Record<string, string>>({});

    const handleAddToCart = async () => {
        console.log("[ADD TO CART]", {
        cart,
        cartId: cart?.id,
        selectedVariant,
        quantity
    });

        if (!cart || !selectedVariant) {
            console.warn("Blocked AddToCart");
            return;
        }

        let activeCart = cart;
        if (!activeCart) {
            activeCart = await createCart();
        }

        addToCartMutation.mutate({
            cartId: activeCart!.id,
            productId: selectedVariant.id,
            amount: quantity,
        });
    }

    if (isLoading) return <p>Loading product...</p>

    if (error) return <p>Error!</p>

    return (
        <div className="product-page">
            <div className="image">
                <div className="card">
                    {selectedVariant && (selectedVariant.salePercentage && selectedVariant?.salePercentage < 1)  && <span className="sale">
                        SALE {Math.round((1 - selectedVariant.salePercentage) * 100)}% OFF</span>}
                    <img src={selectedVariant?.imageURL ?? product?.defaultImageURL} alt={product?.name}/>
                </div>
            </div>

            <div className="options">
                <h1>{product?.name}</h1>
                
                {Array.from(attributeMap.entries()).map(([attributeName, values]) => (
                <div key={attributeName} className="options-row">
                    <b><label>{attributeName} </label></b>

                    <select
                        value={selectedAttributes[attributeName] ?? ""}
                        onChange={(e) =>
                            setSelectedAttributes(data => ({
                                ...data,
                                [attributeName]: e.target.value,
                            }))
                        }
                    >
                        <option value="" disabled>
                            -- Select {attributeName} --
                        </option>
                        {Array.from(values).map(value => (
                            <option key={value} value={value} disabled={isOptionOutOfStock(attributeName, value)}>
                                {value} {isOptionOutOfStock(attributeName, value) ? "(Sold out)" : ""}
                            </option>
                        ))}
                    </select>
                </div>
            ))}
            </div>

            <div className="details">
                <div className="details-info">
                    <p><b>Description:</b> {product?.description}</p>
                    <p><b>Brand:</b> {product?.productBrand}</p>

                    <div>
                        <b>Price: </b>
                        <span style={{ display: "inline-block" }} className={`price ${selectedVariant && selectedVariant.salePercentage < 1 ? "old-price" : ""}`}>
                            {selectedVariant ? `€${(selectedVariant.price * quantity).toFixed(2)}` : "select options to see price"}
                        </span>
                        <span style={{ display: "inline-block", textIndent: 5 }}>
                            {selectedVariant && selectedVariant.salePercentage < 1 && <div className="sale-price">
                            €{(selectedVariant.price * selectedVariant.salePercentage * quantity).toFixed(2)}</div>}
                        </span>
                    </div>
                </div>

                <div className="details-attributes">
                    <h2>Product specifications:</h2>
                    {product?.attributes.map(attr => (
                        <div key={attr.id}>
                            <b>{attr.attribute}:</b> {attr.value}
                        </div>
                    ))}
                </div>

                <div className="details-actions">
                    <div className="quantity">
                        <button
                            onClick={() => setQuantity(q => Math.max(1, q - 1))}
                            disabled={!selectedVariant}
                        >
                            -
                        </button>
                        <input
                            type="number"
                            min={1}
                            max={selectedVariant?.stock ?? 1}
                            value={quantity}
                            onChange={(e) =>
                                setQuantity(Math.min(selectedVariant?.stock ?? 1,
                                    Math.max(1, Number(e.target.value))
                                ))
                            }
                            disabled={!selectedVariant}
                        />
                        <button
                            onClick={() => setQuantity(q => Math.min(selectedVariant!.stock, q + 1))}
                            disabled={!selectedVariant}
                        >
                            +
                        </button>
                        {lowStock && <div style={{ display: "inline-block" }} className="low-stock">Only {selectedVariant.stock} left in stock!</div>}
                    </div>
                    <button
                        disabled={!selectedVariant || selectedVariant.stock === 0}
                        className="add-to-cart"
                        style={{ width: 115 }}
                        onClick={handleAddToCart}
                        >
                        Add to Cart
                    </button>
                    <div className="hide">Please select all options to add this product to your cart</div>
                </div>
            </div>
        </div>
    )
}

export default ProductDetailPage;