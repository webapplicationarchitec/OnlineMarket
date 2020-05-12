package miu.edu.cs545.service;

import miu.edu.cs545.domain.OrderDetail;
import miu.edu.cs545.domain.Seller;

import java.util.List;

public interface OrderDetailService {
    public List<OrderDetail> getDetailByOrderId(Integer id);
}
