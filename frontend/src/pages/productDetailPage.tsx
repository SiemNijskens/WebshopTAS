import { useQuery } from "@tanstack/react-query";
import type { ProductBaseDTO } from "../types/models.d";
import { API_URL } from "../App";
import NavBar from "../components/NavBar";
import { useState } from "react";

interface ProductDetailProps {
    productId: number;
    setProductId: (id: number) => void;
}

const ProductDetailPage = ({ productId, setProductId }: ProductDetailProps) => {

    const [attributeList, setAttributeList] = useState("");
    console.log(attributeList);
    const { data: product, isLoading, error } = useQuery<ProductBaseDTO>({
        queryKey: ['products', productId],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/products/${productId}`);
            if (!response.ok) {
                throw new Error("Failed to fetch products")
            }
            return response.json();
        },
    });

    if (isLoading) { return <p>Loading products...</p> }

    if (error) { return <p>Error!</p> }

    if (product !== undefined) {
        return (
            <>
                <NavBar />
                <div className="card">
                    <>
                        <h3>{product.name} from {product.productBrand} </h3>

                        <p><span style={{ fontWeight: "bold" }}>Productcode:</span> {product.productCode}</p>
                    </><br></br>
                    <>product.img</>
                    <>
                        {product.attributes.map(attribute => (
                            <div key={attribute.id} >
                                <p><span style={{ fontWeight: "bold" }}>{attribute.attribute}:</span> {attribute.value}</p>
                            </div>
                        ))}
                        {product.description}
                    </><br></br>
                    <button onClick={() => setProductId(NaN)}> back to overview</button>
                </div><br></br>
                <>
                    <div style={{ display: "flex", flexWrap: "wrap", gap: "1rem" }}>
                        {product.productVariants.map(productVariant => (
                            <div key={productVariant.id} className="small card"   >
                                <p><span style={{ fontWeight: "bold" }}>Color:</span> {productVariant.color}</p>
                                <p><span style={{ fontWeight: "bold" }}>Price: </span>€{productVariant.price}</p>
                                <p><span style={{ fontWeight: "bold" }}>Sale:</span>{productVariant.salePercentage}%</p>
                                <p><span style={{ fontWeight: "bold" }}>In Stock:</span>{productVariant.stock}</p>
                                <div>{productVariant.attributes.map(attribute => (
                                    <div key={attribute.id}>
                                        <p>{productVariant.color}</p>
                                        <p><span style={{ fontWeight: "bold" }}>{attribute.attribute}: </span>{attribute.value}</p>
                                    </div>
                                ))}</div>
                                <button>substract </button>
                                <> amount</>
                                <button>add </button>
                                <button>add to cart</button>
                            </div>
                        ))}
                    </div>
                    <div>
                        {product.productVariants.flatMap(poductVariant => (
                            poductVariant.attributes.map(attribute => (
                                <div key={attribute.id}>
                                    {!attributeList.includes(attribute.attribute) &&
                                        <>
                                            <p><span style={{ fontWeight: "bold" }}>{attribute.attribute}: </span>{attribute.value}</p>                                            <select name={attribute.attribute}>
                                                {poductVariant.attributes.map(attribute2 => (
                                                    <>{attribute.attribute === attribute2.attribute && <>
                                                        <option value={attribute.value}>{attribute2.value}</option>
                                                    </>}</>
                                                ))}
                                            </select>
                                            {() => setAttributeList(attributeList + attribute.attribute)}
                                        </>
                                    }
                                </div>
                            ))
                        ))}
                    </div>
                </>
            </>
        )
    }
}

export default ProductDetailPage;