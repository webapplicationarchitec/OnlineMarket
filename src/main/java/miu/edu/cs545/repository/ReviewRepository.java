package miu.edu.cs545.repository;

import miu.edu.cs545.domain.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {

    @Query("select p from Review  p where p.status=0")
    public List<Review> getNewReviews();

    @Query("select  p from Review  p where  p.product.id=:id")
    public  List<Review> getByProductId(Integer id);

    @Modifying
    @Query("update Review s set s.status=:status where s.id=:id")
    public void approveNewReview(@Param("status") ReviewStatus status, @Param("id") Integer id);

    public List<Review> findAllByStatus(ReviewStatus rev);
    public List<Review> findAllByStatusAndAndProduct(ReviewStatus rev, Product pro);

}
