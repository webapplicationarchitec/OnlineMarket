package miu.edu.cs545.domain;

import java.util.Date;

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

    public Date getDate_create() {
        return date_create;
    }

    Date date_create;
    Date date_shipping;

    public Integer getId() {
        return id;
    }

    public Date getDate_shipping() {
        return date_shipping;
    }

    public Date getDate_delivered() {
        return date_delivered;
    }

    public Integer getTax() {
        return tax;
    }

    public Integer getShippingFee() {
        return shippingFee;
    }

    public Double getTotal() {
        return total;
    }

    public Integer getPoint() {
        return point;
    }

    public String getOrderno() {
        return orderno;
    }

    public OrderStatus getStatus() {
        return status;
    }

    Date date_delivered;
    Integer tax;
    Integer shippingFee;
    Double total;
    Integer point;
    String orderno;
    OrderStatus status;
}
