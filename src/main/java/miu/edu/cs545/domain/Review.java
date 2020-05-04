package miu.edu.cs545.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
public class Review {
    Integer id;
    Integer productId;
    Date dateCreate;
    String comment;

    public Review() {
    }

    public Review(Integer id, Integer productId, Date dateCreate, String comment, ReviewStatus status) {
        this.id = id;
        this.productId = productId;
        this.dateCreate = dateCreate;
        this.comment = comment;
        this.status = status;
    }

    ReviewStatus status;

}
