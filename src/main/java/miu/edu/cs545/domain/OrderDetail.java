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
    Integer id;
    Integer orderId;
    Order order;
    Integer productId;
    Product product;
    Double price;
    Integer qty;
}
