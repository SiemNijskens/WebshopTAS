export interface ProductBaseDTO {
    id: number,
    productCode: string,
    name: string,
    description: string,
    productBrand: string,
    productVariants: ProductDTO[],
    attributes: ProductAttributeDTO[],
}

export interface ProductDTO {
    id: number,
    price: number,
    salePercentage: number,
    stock: number,
    color: string,
    attributes: ProductAttributeDTO[],
}

export interface ProductAttributeDTO {
    id: number,
    attribute: string,
    value: string,
    type: "PRODUCT" | "VARIANT",
}

export interface LoginDTO {
    email: string,
    password: string,
}

export interface UserSummaryDTO {
    firstName: string,
    lastName: string,
    roles: string[],
}

export interface UserDTO {
    id: number,
    firstName: string,
    lastName: string,
    roles: string[],
    email: string,
    zipCode: string,
    houseNumber: string,
    streetName: string,
    city: string,
}