import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import type { ProductAttributeDTO, ProductBaseDTO, ProductDTO } from "../types/models";
import { API_URL } from "../App";
import { Accordion, AccordionItem } from '@szhsin/react-accordion';
import { useState } from "react";

const ProductList = () => {

    const [formData, setFormData] = useState({})
    const [editedStock, setEditedStock] = useState({});

    const queryClient = useQueryClient();

    const updateStockToBackend = useMutation({
        mutationFn: async (formData) => {
            const response = await fetch(`${API_URL}/products/stock`, {
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData)
            });

            if (!response.ok) {
                throw new Error('Failed to create product')
            };

            return response.json();
        },

        onSuccess: (data) => {
            // eslint-disable-next-line @typescript-eslint/no-unused-expressions
            queryClient.invalidateQueries({ queryKey: ["products"] }),
                console.log('products created successfully', data),
                setFormData({ stock: NaN, variantId: NaN });
        }
    })

    const {
        data: products,
        isLoading,
        error,
    } = useQuery<ProductBaseDTO[]>({
        queryKey: ["products"],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/products`);
            if (!response.ok) {
                throw new Error("Failed to fetch products");
            }
            return response.json();
        },
    });

    if (isLoading) {
        return <div>Loading products...</div>;
    }

    if (error) {
        return <div style={{ color: "red" }}>Error: {error.message}</div>;
    }

    const handleStockChange = (variantId, newStock) => {
        setFormData({... formData,
            "variantId": variantId,
            "newStock": newStock
        });
    };

    const updateStock = () => {
        event?.preventDefault()
        console.log(formData)
        console.log(JSON.stringify(formData))
        updateStockToBackend.mutate(formData);

    }

    return (<>
        <div>
            <h2>products</h2>
            {products && products.length > 0 ? (
                <ul>
                    {products.map((product: ProductBaseDTO) => (
                        <div key={product.id}>
                            <Accordion>
                                <AccordionItem header={product.name} >
                                    <strong>Brand:</strong> {product.productBrand} <button> edit base product </button> <br />
                                    <strong>variants:</strong>
                                    {product.productVariants.map((variant: ProductDTO) => (
                                        <li key={variant.id}>
                                            Stock: {variant.stock}
                                            {variant.attributes.map((attribute: ProductAttributeDTO) => (
                                                <span key={attribute.id}> | {attribute.attribute}: {attribute.value}
                                                </span>
                                            ))} <input type="number" defaultValue={formData [variant.id] ?? variant.stock} placeholder="new stock" 
                                            onChange={e => handleStockChange(variant.id, Number(e.target.value))} ></input><button onClick={() => (updateStock())}> update Stock </button>
                                            {/* <Form.Control value={formData.productDescription} name="productDescription" placeholder="basic t-shirt" onChange={() => handleChangeBaseProduct(event)} /> */}
                                        </li>
                                    ))}
                                </AccordionItem>
                            </Accordion>
                        </div>
                    ))}
                </ul>
            ) : (
                <p>No users found</p>
            )}
        </div>
    </>
    );
};

export default ProductList