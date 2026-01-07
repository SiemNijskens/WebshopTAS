import { useLocation } from "react-router";
import "../styles/sidebar.css"
import type { ProductFilters } from "../types/models";
import { useEffect } from "react";

interface SideBarProps {
    filters: ProductFilters;
    setFilters: React.Dispatch<React.SetStateAction<ProductFilters>>;
    onApply: () => void;
    isOpen: boolean;
    onClose: () => void;
}

const Sidebar = ({ filters, setFilters, onApply, isOpen, onClose }: SideBarProps) => {

    const location = useLocation();
    const isOverviewPage = location.pathname === "/products";

    useEffect(() => {
        if (!isOpen) return;

        const handler = (e: KeyboardEvent) => {
            if (e.key === "Escape") onClose();
        };
        window.addEventListener("keydown", handler);
        return () => window.removeEventListener("keydown", handler);
    }, [isOpen, onClose]);

    const CATEGORIES = ["T-Shirts", "Pants", "Shoes", "Hoodies", "Caps", "Socks", "Other"];
    const BRANDS = ["Generic", "Webshop", "In Da Hood", "Gramfel", "Mozza", "Iphoney", "Mike"]
    // const COLORS = ["Red", "Blue", "White", "Black", "Orange", "Gray", "Khaki"];
    // const SIZES = ["XS", "S", "M", "L", "XL"];

    const handleCheckboxChange = (
        group: "categories" | "brands" | "sizes",
        value: string,
        checked: boolean
    ) => {
        setFilters(data => ({
            ...data,
            [group]: checked
            ? [...data[group], value]
            : data[group].filter(val => val !== value),
        }));
    };

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        onApply();
        onClose();
    };

    return (
        <form onSubmit={handleSubmit}>
            {isOpen && (
            <div className="sidebar-overlay" onClick={onClose}>
                {/* <div className={`sidebar ${isOpen ? "open" : "" }`}> */}
                <div className="sidebar" onClick={(e) => e.stopPropagation()}>
                    <button className="close" onClick={onClose}>X</button>

                    Categories
                    {CATEGORIES.map(category => (
                        <div key={category}>
                            <input
                                type="checkbox"
                                checked={filters.categories.includes(category)}
                                onChange={(e) => 
                                    handleCheckboxChange("categories", category, e.target.checked)
                                }
                            />
                            <label>{category}</label>
                        </div>
                    ))}
                    <br/>
                    Brands
                    {BRANDS.map(brand => (
                        <div key={brand}>
                            <input
                                type="checkbox"
                                checked={filters.brands.includes(brand)}
                                onChange={(e) =>
                                    handleCheckboxChange("brands", brand, e.target.checked)
                                }
                            />
                            <label>{brand}</label>
                        </div>
                    ))}
                    <br/>
                {/* Sizes
                {SIZES.map(size => (
                    <div key={size}>
                        <input
                            type="checkbox"
                            checked={filters.sizes.includes(size)}
                            onChange={(e) =>
                                handleCheckboxChange("sizes", size, e.target.checked)
                            }
                        />
                        <label>{size}</label>
                    </div>
                ))}
                <br/> */}
                    <input
                        type="checkbox"
                        checked={filters.onSale ?? false}
                        onChange={(e) => 
                            setFilters(data => ({
                                ...data,
                                onSale: e.target.checked,
                            }))
                        }
                    />
                    <label>On Sale</label>
                    <br/>
                    {!isOverviewPage && <button type="submit">Apply Filters</button>}
                </div>
            </div>)}
        </form>
    )
}

export default Sidebar;