package miu.edu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    Integer id;
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
