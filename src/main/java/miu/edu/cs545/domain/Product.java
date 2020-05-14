package miu.edu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotEmpty(message = "{error.string.empty}")
    @Size(min=3, max=20, message = "{error.size}")
    private String name;
    @NotEmpty(message = "{error.string.empty}")
    @Size(min=10, max=100, message = "{error.size}")
    private String description;
    @NotNull(message = "{error.any.null}")
    @Range(min=0, message="{error.number.range}")
    private Double price;
    @NotNull(message = "{error.any.null}")
    @Range(min=0, message="{error.number.range}")
    private Integer point;
    @NotEmpty(message = "{error.string.empty}")
    private String photo;
    private ProductStatus status;

    @Transient
    private MultipartFile image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Review> listReview;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private Seller seller;
}