package miu.edu.cs545.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.edu.cs545.domain.OnlineOrder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Cart {
    private List<OnlineOrder> orderList;
}
