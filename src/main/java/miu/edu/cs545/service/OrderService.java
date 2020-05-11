package miu.edu.cs545.service;

import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.OrderDetail;
import miu.edu.cs545.exception.OrderCreateException;

import java.util.List;

public interface OrderService {

    public void placeOrder(OnlineOrder order) throws OrderCreateException;

    public List<OnlineOrder> getAll();

    public OnlineOrder getOrder(Integer id);

    public OnlineOrder getOrder(String orderNo);

    public OnlineOrder getOrderWithDetails(String orderNo);

    public OnlineOrder getOrderWithDetails(Integer id);
}
