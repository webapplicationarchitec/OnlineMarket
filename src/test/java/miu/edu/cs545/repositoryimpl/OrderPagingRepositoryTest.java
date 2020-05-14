package miu.edu.cs545.repositoryimpl;

import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.Product;
import miu.edu.cs545.domain.Seller;
import miu.edu.cs545.repository.OrderPagingRepository;
import miu.edu.cs545.repository.ProductPagingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.assertEquals;


@DataJpaTest
public class OrderPagingRepositoryTest {
    @Autowired
    OrderPagingRepository orderPagingRepository;

    @Test
    public void findAll() throws Exception{

        List<String> properties = Arrays.asList("name");

        Pageable sortedByDateCreate =
                PageRequest.of(0, 100, Sort.by("dateCreate").descending());

       // Pageable sortedByPriceDesc =
         //       PageRequest.of(0, 3, Sort.by("price").descending());

        //Pageable sortedByPriceDescNameAsc =
         //       PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));

        Page<OnlineOrder> orders = orderPagingRepository.findAll(sortedByDateCreate);

         assertEquals(2,  orders.getTotalElements());

//        assertEquals(8,  productOptional.getTotalElements());
    }

    @Test
    public void findBySeller() throws Exception{

        List<String> properties = Arrays.asList("name");
        Seller seller =new Seller();
        seller.setUsername("hainguyen");


        Pageable sortedByDateCreate =
                PageRequest.of(0, 100, Sort.by("dateCreate").descending());

        // Pageable sortedByPriceDesc =
        //       PageRequest.of(0, 3, Sort.by("price").descending());

        //Pageable sortedByPriceDescNameAsc =
        //       PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));

        Page<OnlineOrder> orders = orderPagingRepository.findOnlineOrdersBySeller(seller,sortedByDateCreate);

        assertEquals(2,  2);

    }
}
