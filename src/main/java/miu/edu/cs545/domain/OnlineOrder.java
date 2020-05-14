package miu.edu.cs545.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OnlineOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date dateDelivered;

    @NotNull
    @Future
    private Date dateShipping;

    private Double tax;
    private Date dateCreate;
    private Double shippingFee;
    private Double total;
    private Integer point;
    private Integer usedPoint;
    private String orderno;
    private String shippingAddress;
    private OrderStatus status;

    public String getsStatus() {
        return status.name();
    }

    @Transient
    private String sStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private List<OrderDetail> orderDetailList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sellerId")
    private Seller seller;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyerId")
    private Buyer buyer;


}
