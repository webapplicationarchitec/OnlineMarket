package miu.edu.cs545.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
public class Order {
    Integer id;

    public Order(Integer id, Date date_create, Date date_shipping, Date date_delivered, Integer tax, Integer shippingFee, Double total, Integer point, String orderno, OrderStatus status) {
        this.id = id;
        this.date_create = date_create;
        this.date_shipping = date_shipping;
        this.date_delivered = date_delivered;
        this.tax = tax;
        this.shippingFee = shippingFee;
        this.total = total;
        this.point = point;
        this.orderno = orderno;
        this.status = status;

    }

    public Order() {
    }

    Date date_create;
    Date date_shipping;
    Date date_delivered;
    Integer tax;
    Integer shippingFee;
    Double total;
    Integer point;
    String orderno;
    OrderStatus status;
}
