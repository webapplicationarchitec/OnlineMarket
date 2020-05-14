package miu.edu.cs545.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.edu.cs545.domain.Buyer;
import miu.edu.cs545.domain.OnlineOrder;

@Getter
@Setter
@NoArgsConstructor
public class CheckOutModel {
    private Buyer buyer;
    private OnlineOrder order;
    private Integer usedPoints;
    private Integer points;
}
