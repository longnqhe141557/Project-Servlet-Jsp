package entity;

public class BillDetail {

    private String productId, orderId;
    private int quantityPurchased;
    private double priceOfProduct, total;

    public BillDetail() {
    }

    public BillDetail(String productId, String orderId, int quantityPurchased, double priceOfProduct, double total) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantityPurchased = quantityPurchased;
        this.priceOfProduct = priceOfProduct;
        this.total = total;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public double getPriceOfProduct() {
        return priceOfProduct;
    }

    public void setPriceOfProduct(double priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
