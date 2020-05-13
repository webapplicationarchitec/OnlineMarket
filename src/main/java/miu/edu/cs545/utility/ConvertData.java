package miu.edu.cs545.utility;

import miu.edu.cs545.domain.OrderDetail;
import miu.edu.cs545.dto.OrderDetailDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ConvertData {
    public static <T> List<T> IterableToList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public static OrderDetailDTO OrderDetailToOrderDetailDTO(OrderDetail orderDetail){
        OrderDetailDTO detailDTO=new OrderDetailDTO();
        detailDTO.setProductname(orderDetail.getProduct().getName());
        detailDTO.setPrice(orderDetail.getSellPrice().toString());
        detailDTO.setQuantity(orderDetail.getQty().toString());
        detailDTO.setTotal(orderDetail.getTotal().toString());
        return detailDTO;

    }

    public static List<OrderDetailDTO> OrderDetailsToOrderDetailDTOs(List<OrderDetail> orderDetails){

        List<OrderDetailDTO> result = new ArrayList<>();

        for (OrderDetail or: orderDetails) {
            result.add(OrderDetailToOrderDetailDTO(or));
        }

        return result;

    }
}
