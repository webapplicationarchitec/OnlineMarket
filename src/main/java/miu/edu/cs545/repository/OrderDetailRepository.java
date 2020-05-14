package miu.edu.cs545.repository;

import miu.edu.cs545.domain.Category;
import miu.edu.cs545.domain.OrderDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
    @Query(value="SELECT * FROM ORDER_DETAIL  Where Order_id= ?1",  nativeQuery = true )
    public List<OrderDetail> getDetailsByOrderId(Integer id);

    @Modifying
    @Query(value="DELETE FROM ORDER_DETAIL  Where PRODUCT_ID= ?1",  nativeQuery = true )
    public void deleteByProductId(Integer id);
}
