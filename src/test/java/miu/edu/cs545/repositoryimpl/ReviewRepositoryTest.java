package miu.edu.cs545.repositoryimpl;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import miu.edu.cs545.domain.Review;
import miu.edu.cs545.domain.ReviewStatus;
import miu.edu.cs545.repository.AccountRepository;
import miu.edu.cs545.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
public class ReviewRepositoryTest {
    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void findNewReview() throws Exception{
        List<Review> reviews = reviewRepository.getNewReviews();
        assertEquals(2, reviews.size());
    }

    @Test
    public void approveNewSellerAccount() throws Exception{
        reviewRepository.approveNewReview(ReviewStatus.Approved,1);
        assertEquals(2, 2);
    }
}
