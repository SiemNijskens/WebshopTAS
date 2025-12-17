import { useQuery } from "@tanstack/react-query";
import type { ProductBaseDTO, ProductDTO } from "../types/models.d";
import { API_URL } from "../App";


const ProductList = () => {

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

    if (isLoading) { return <p>Loading products...</p>}

    if (error) { return <p>Error!</p>}

    return (
        <div style={{ display: "flex", flexWrap: "wrap", gap: "1rem" }}>
            {products?.map(product => (
                    <div key={product.id} className="card">
                        <h3 style={{ }}>{product.name}</h3>
                    </div>
                ))
            }
        </div>
    );

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

export default ProductList;