package miu.edu.cs545.service;

import miu.edu.cs545.domain.*;

import java.util.List;

public interface ReviewService {
   public List<Review> getNewReviews();
   public  List<Review> getByProductId(Integer id);
   public  void  approveNewReview(ReviewStatus status, Integer id);
   public Review addnew(Review rev);
   public List<Review> getListReviewsByStatusAndProduct(ReviewStatus reviewStatus, Product pro);
}
