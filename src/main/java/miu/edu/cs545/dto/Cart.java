package miu.edu.cs545.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.Seller;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Cart {
    private int total;
    private HashMap<String, OnlineOrder> orderList = new HashMap<>();
}
