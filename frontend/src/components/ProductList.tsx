import { useQuery } from "@tanstack/react-query";
import type { ProductBaseDTO, ProductDTO } from "../types/models";
import { API_URL } from "../App";

const ProductList = () => {

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

    return (<>
        <div>
            <h2>products</h2>
            {products && products.length > 0 ? (
                <ul>
                    {products.map((product: ProductBaseDTO) => (
                        <li key={product.id}>
                            {product.name} {product.productBrand} <button> edit product </button>
                            {product.productVariants.map((variant: ProductDTO) => (
                                <li key={variant.id}>
                                    {variant.attributes.map()}

                                </li>
                            ))}
                        </li>
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