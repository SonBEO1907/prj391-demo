package entity;

/**
 *
 * @author os
 */
public class Categories {
    int categoryId;
    String categoryName;
    int productId;

    public Categories(int categoryId, String categoryName, int productId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
}
