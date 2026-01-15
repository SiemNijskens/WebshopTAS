import { useQuery } from "@tanstack/react-query";
import type { ProductBaseDTO, ProductFilters } from "../types/models";
import { API_URL } from "../App";
import '../styles/card.css';
import { useNavigate, useOutletContext } from "react-router";
 
 
const productOverviewPage = () => {
    const navigate = useNavigate();
    const { filters } = useOutletContext<{ filters: ProductFilters }>();
 
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
 
    const filteredProducts = products?.filter(product => {
        if (filters.categories.length && !filters.categories.includes(product.category)) {
            return false;
        }

        if (filters.brands.length && !filters.brands.includes(product.productBrand)) {
            return false;
        }

        if (filters.onSale) {
            const hasSaleVariant = product.productVariants.some(
                v => v.salePercentage < 1);
            if (!hasSaleVariant) return false;
        }

        return true;
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

    if (filteredProducts !== undefined) {
        return (
            <>
                <div style={{ display: "flex", flexWrap: "wrap", gap: "1rem" }}>
                    {filteredProducts.map(product => (
                        <div key={product.id} onClick={() => navigate(`/products/${product.id}`)} className="card">
                            <h3 style={{}}>{product.name} from {product.productBrand}</h3>
                            <p>{product.description}</p>
                            <img src={product.defaultImageURL} alt={product.name} style={{ height: 250 }}/>
                            {product.productVariants.some(variant => variant.salePercentage < 1 ) && <span className="sale-overview">SALE</span>}
                        </div>
                    ))
                    }
                </div>
            </>
        );
    }

    // if (products !== undefined) {
    //     return (
    //         <>
    //             <div style={{ display: "flex", flexWrap: "wrap", gap: "1rem" }}>
    //                 {products.map(product => (
    //                     <div key={product.id}  onClick={() => setProductId(product.id)} className="card">
    //                         <h3 style={{}}>{product.name} from {product.productBrand}</h3>
    //                         <p>{product.description}</p>
    //                         <p>place img here</p>
    //                         <h4>attributes</h4>
    //                         {product.attributes.map(attribute => (
    //                             <div key={attribute.id}>
    //                             <p>{attribute.attribute}: {attribute.value}</p>
    //                             </div>
    //                         ))}
    //                     </div>
    //                 ))
    //                 }
    //             </div>
    //         </>
    //     );
    // }
 
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