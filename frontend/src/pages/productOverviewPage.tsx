import { useQuery } from "@tanstack/react-query";
import type { ProductBaseDTO } from "../types/models";
import { API_URL } from "../App";
import { useState } from "react";
import ProductDetailPage from "./productDetailPage";
 
 
const productOverviewPage = () => {
    const [productId, setProductId] = useState(NaN);
    console.log("current productId " + productId);
 
    const { data: products, isLoading, error } = useQuery<ProductBaseDTO[]>({
        queryKey: ['products'],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/products`);
            if (!response.ok) {
                throw new Error("Failed to fetch products")
            }
            return response.json();
        },
    });
 
    // const getColorVariants = (product: ProductBaseDTO) => {
    //     const map = new Map<string, ProductDTO>();
 
    //     product.productVariants.forEach(variant => {
    //         if (!map.has(variant.color)) {
    //         map.set(variant.color, variant);
    //         }
    //     });
    //     return Array.from(map.values());
    // }
 
    if (isLoading) { return <p>Loading products...</p> }
 
    if (error) { return <p>Error!</p> }
 
    if (productId) {
        return (
        <ProductDetailPage productId={productId} setProductId={setProductId}/>
        )
    }
 
    if (products !== undefined) {
        return (
            <>
                <div style={{ display: "flex", flexWrap: "wrap", gap: "1rem" }}>
                    {products.map(product => (
                        <div key={product.id}  onClick={() => setProductId(product.id)} className="card">
                            <h3 style={{}}>{product.name} from: {product.productBrand}</h3>
                            <p>{product.description}</p>
                            <p>place img here</p>
                            <h4>attributes</h4>
                            {product.attributes.map(attribute => (
                                <div key={attribute.id}>
                                <p>{attribute.attribute}: {attribute.value}</p>
                                </div>
                            ))}
                        </div>
                    ))
                    }
                </div>
            </>
        );
    }
 
    // return (
    //     <div style={{ display: "flex", flexWrap: "wrap", gap: "1rem" }}>
    //         {products?.flatMap(product => (
    //             product.productVariants.map(variant => (
    //                 <div key={variant.id} className="card">
    //                     <h3>{product.name}</h3>
    //                     <p>{variant.color}</p>
    //                     <p>{variant.price}</p>
    //                 </div>
    //             ))
    //         ))}
    //     </div>
    // );
}
 
export default productOverviewPage;