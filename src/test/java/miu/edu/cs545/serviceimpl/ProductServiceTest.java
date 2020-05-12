package miu.edu.cs545.serviceimpl;


import miu.edu.cs545.builder.ProductListBuilder;
import miu.edu.cs545.domain.Product;
import miu.edu.cs545.repository.ProductPagingRepository;
import miu.edu.cs545.repository.ProductRepository;
import miu.edu.cs545.service.ProductServiceImp;
//import org.junit.Before;
//import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

//import static org.junit.Assert.assertEquals;

public class ProductServiceTest {
    ProductServiceImp productServiceImp;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductPagingRepository productPagingRepository;

//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        productServiceImp = new ProductServiceImp(productPagingRepository,productRepository);
//    }
//
//    @Test
//    public void getAll() {
//        List<Product> products = new ProductListBuilder().build();
//
//        Mockito.when(productRepository.findAll()).thenReturn(products);
//
//        List<Product> result = productServiceImp.all();
//        assertEquals(2, result.size());
//
//        //verify getAll() is called only once
//      //  Mockito.verify(categoryRepository, Mockito.times(1)).getAll();
//
//    }
}
