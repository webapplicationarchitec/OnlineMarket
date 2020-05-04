package miu.edu.cs545.domain;

public class OrderDetail {
    Integer id;
    Integer orderId;

    public OrderDetail(Integer id, Integer orderId, Order order, Integer productId, Product product, Double price, Integer qty) {
        this.id = id;
        this.orderId = orderId;
        this.order = order;
        this.productId = productId;
        this.product = product;
        this.price = price;
        this.qty = qty;
    }

    Order order;
    Integer productId;

    public Integer getId() {
        return id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Order getOrder() {
        return order;
    }

    public Integer getProductId() {
        return productId;
    }

    public Product getProduct() {
        return product;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQty() {
        return qty;
    }

    Product product;
    Double price;
    Integer qty;

}
