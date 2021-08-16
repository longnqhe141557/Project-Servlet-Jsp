package entity;

public class Bill {

    private String orderId, dateCreate, customerName, customerPhone, customerAddress;
    private double total;
    private int status, customerId;

    public Bill() {
    }

    public Bill(String orderId, String dateCreate, String customerName, String customerPhone, String customerAddress, double total, int status, int customerId) {
        this.orderId = orderId;
        this.dateCreate = dateCreate;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.total = total;
        this.status = status;
        this.customerId = customerId;
    }

    public Bill(String orderId, String dateCreate, String customerName, String customerPhone, String customerAddress, double total) {
        this.orderId = orderId;
        this.dateCreate = dateCreate;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
}
