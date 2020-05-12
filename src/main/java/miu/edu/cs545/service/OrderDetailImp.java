package miu.edu.cs545.service;

import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.OrderDetail;
import miu.edu.cs545.domain.OrderStatus;
import miu.edu.cs545.exception.OrderCreateException;
import miu.edu.cs545.repository.OrderDetailRepository;
import miu.edu.cs545.repository.OrderPagingRepository;
import miu.edu.cs545.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderDetailImp implements OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetail> getDetailByOrderId(Integer id) {
        return orderDetailRepository.getDetailsByOrderId(id);
    }
}
