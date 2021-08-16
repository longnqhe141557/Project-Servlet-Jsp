package entity;

public class Product {

    private String productId, productName;
    private int quantity;
    private double priceOfProduct;
    private String productImage, productDescription;
    private int status, categoryId;

    public Product() {
    }

    public Product(String productId, String productName, int quantity, double priceOfProduct, String productImage, String productDescription, int status, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.priceOfProduct = priceOfProduct;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.status = status;
        this.categoryId = categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceOfProduct() {
        return priceOfProduct;
    }

    public void setPriceOfProduct(double priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", quantity=" + quantity + ", priceOfProduct=" + priceOfProduct + ", productImage=" + productImage + ", productDescription=" + productDescription + ", status=" + status + ", categoryId=" + categoryId + '}';
    }

}
