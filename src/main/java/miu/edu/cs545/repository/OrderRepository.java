package miu.edu.cs545.repository;

import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.OrderStatus;
import miu.edu.cs545.domain.ReviewStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<OnlineOrder, Integer> {

    @Modifying
    @Query("update OnlineOrder s set s.status=:status where s.id=:id")
    public void updateStatus(@Param("status") OrderStatus status, @Param("id") Integer id);

    @Modifying
    @Query("update OnlineOrder s set s.dateDelivered=:date where s.id=:id")
    public void updateDateDelivered(@Param("date") Date date, @Param("id") Integer id);

    @Query("select p from OnlineOrder p where p.buyer.username= :username")
    public List<OnlineOrder> getByBuyer(@Param("username") String username);

}
