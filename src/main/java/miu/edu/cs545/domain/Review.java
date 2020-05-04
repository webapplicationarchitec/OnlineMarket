package miu.edu.cs545.domain;

import java.util.Date;

public class Review {
    Integer id;
    Integer productId;
    Date dateCreate;
    String comment;

    public Review(Integer id, Integer productId, Date dateCreate, String comment, ReviewStatus status) {
        this.id = id;
        this.productId = productId;
        this.dateCreate = dateCreate;
        this.comment = comment;
        this.status = status;
    }

    ReviewStatus status;

    public Integer getId() {
        return id;
    }

    public Integer getProductId() {
        return productId;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public String getComment() {
        return comment;
    }

    public ReviewStatus getStatus() {
        return status;
    }
}
