package miu.edu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String description;
    private Double price;
    private Integer point;
    private String photo;
    private ProductStatus status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="categoryId")
    private Category category;

    @OneToMany(mappedBy="product")
    private List<Review> listReview;
}