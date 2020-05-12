package miu.edu.cs545.repository;

import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.domain.OrderStatus;
import miu.edu.cs545.domain.ReviewStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends CrudRepository<OnlineOrder, Integer> {

    @Modifying
    @Query("update OnlineOrder s set s.status=:status where s.id=:id")
    public void updateStatus(@Param("status") OrderStatus status, @Param("id") Integer id);

}
