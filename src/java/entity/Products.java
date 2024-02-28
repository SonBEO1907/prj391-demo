package entity;

/**
 *
 * @author os
 */
public class Products {
    int productId;
    String productName, productType;
    int productQuantity;
    String description;
    double price;
    int status;

    public Products(int productId, String productName, String productType, int productQuantity, String description, double price, int status) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productQuantity = productQuantity;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
    
}
