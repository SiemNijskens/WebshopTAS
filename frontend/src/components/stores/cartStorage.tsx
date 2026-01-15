const CART_ID_KEY = "cartId";

export const  getStoredCartId = (): number | null => {
    const id = localStorage.getItem(CART_ID_KEY);
    return id ? Number(id) : null;
};

export const storeCartId = (id: number) => {
    localStorage.setItem(CART_ID_KEY, id.toString());
};

export const clearStoredCartId = () => {
    localStorage.removeItem(CART_ID_KEY);
};