package miu.edu.cs545.service;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import miu.edu.cs545.domain.Review;
import miu.edu.cs545.domain.ReviewStatus;
import miu.edu.cs545.repository.AccountRepository;
import miu.edu.cs545.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewServiceImp implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Review> getNewReviews() {
        return reviewRepository.getNewReviews();
    }

    @Override
    public List<Review> getByProductId(Integer id) {
        return reviewRepository.getByProductId(id);
    }

    @Override
    public void approveNewReview(ReviewStatus status, Integer id) {
        reviewRepository.approveNewReview(status,id);
    }
}
