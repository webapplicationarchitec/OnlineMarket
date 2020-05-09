package miu.edu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private Integer id;
    private Integer orderId;
    private Order order;
    private Integer productId;
    private Product product;
    private Double price;
    private Integer qty;
}
