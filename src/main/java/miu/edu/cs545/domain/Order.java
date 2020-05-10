package miu.edu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany
    @JoinColumn(name = "orderId")
    private List<OrderDetail> listOrderDetail;


}
