package miu.edu.cs545.service;

import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.OrderDetail;
import miu.edu.cs545.domain.OrderStatus;
import miu.edu.cs545.exception.OrderCreateException;
import miu.edu.cs545.repository.OrderPagingRepository;
import miu.edu.cs545.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderPagingRepository orderPagingRepository;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository, OrderPagingRepository orderPagingRepository) {
        this.orderRepository = orderRepository;
        this.orderPagingRepository=orderPagingRepository;
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

    @Override
    public Page<OnlineOrder> paging(Pageable pageable) {
        return orderPagingRepository.findAll(pageable);
    }

    @Override

    public void updateStatus(OrderStatus status, Integer id) {

        if(status==OrderStatus.Delivered) orderRepository.updateDateDelivered(new Date(),id);
        else  orderRepository.updateDateDelivered(null,id);

        orderRepository.updateStatus(status, id);

    }

    @Override
    public List<OnlineOrder> getByBuyer(String userName) {
        return orderRepository.getByBuyer(userName);
    }
}
