package miu.edu.cs545.service;

import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.OrderDetail;
import miu.edu.cs545.exception.OrderCreateException;
import miu.edu.cs545.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void placeOrder(OnlineOrder order) throws OrderCreateException {
        try {
            orderRepository.save(order);
        } catch (Exception e) {
            throw new OrderCreateException("Could not create exception");
        }
    }

    public List<OnlineOrder> getAll() {
        return null;
    }

    public OnlineOrder getOrder(Integer id) {
        return null;
    }

    public OnlineOrder getOrder(String orderNo) {
        return null;
    }

    public OnlineOrder getOrderWithDetails(String orderNo) {
        return null;
    }

    public OnlineOrder getOrderWithDetails(Integer id) {
        return null;
    }
}
