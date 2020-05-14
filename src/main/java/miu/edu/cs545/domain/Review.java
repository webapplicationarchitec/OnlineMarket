package miu.edu.cs545.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date dateCreate;
    private String comment;
    private ReviewStatus status;

    @ManyToOne //(cascade = CascadeType.ALL)
    @JoinColumn(name="productId")
    private Product product;

    @ManyToOne //(cascade = CascadeType.ALL)
    @JoinColumn(name="buyerId")
    private Buyer buyer;

    @ManyToOne //(cascade = CascadeType.ALL)
    @JoinColumn(name="sellerId")
    private Seller seller;



}
