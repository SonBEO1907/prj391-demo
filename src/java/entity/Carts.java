package entity;

/**
 *
 * @author os
 */
public class Carts {
    int cartId;
    String customerUsername;
    int productId, quantity;

    public Carts(int cartId, String customerUsername, int productId, int quantity) {
        this.cartId = cartId;
        this.customerUsername = customerUsername;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
