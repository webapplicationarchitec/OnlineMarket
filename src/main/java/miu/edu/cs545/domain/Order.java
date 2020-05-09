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
    private Integer id;
    private Date dateDelivered;
    private Date dateShipping;
    private Integer tax;
    private Date dateCreate;
    private Double shippingFee;
    private Double total;
    private Integer point;
    private String orderno;
    private OrderStatus status;
}
