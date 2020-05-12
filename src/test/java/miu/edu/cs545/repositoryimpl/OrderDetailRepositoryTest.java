package miu.edu.cs545.repositoryimpl;

import miu.edu.cs545.domain.OrderDetail;
import miu.edu.cs545.domain.ReviewStatus;
import miu.edu.cs545.repository.OrderDetailRepository;
import miu.edu.cs545.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
public class OrderDetailRepositoryTest {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void getListDetailByProductId() throws Exception{
       List<OrderDetail> orders = orderDetailRepository.getDetailsByOrderId(202);
        assertEquals(2, orders.size());
    }

}
