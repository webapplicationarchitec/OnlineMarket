package miu.edu.cs545.service;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import miu.edu.cs545.domain.Review;
import miu.edu.cs545.domain.ReviewStatus;

import java.util.List;

public interface ReviewService {
   public List<Review> getNewReviews();
   public  List<Review> getByProductId(Integer id);
   public  void  approveNewReview(ReviewStatus status, Integer id);
}
