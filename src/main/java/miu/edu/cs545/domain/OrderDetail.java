package miu.edu.cs545.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
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

    public OrderDetail() {
    }

    Order order;
    Integer productId;
    Product product;
    Double price;
    Integer qty;

}
