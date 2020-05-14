package miu.edu.cs545.repositoryimpl;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.repository.AccountRepository;
import miu.edu.cs545.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;



    @Test
    public void getOrderByBuyer() throws Exception{
       List<OnlineOrder> onlineOrders = orderRepository.getByBuyer("hanguyen");
        assertEquals(2, onlineOrders.size());
    }



}
