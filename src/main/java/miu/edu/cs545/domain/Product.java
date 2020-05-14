package miu.edu.cs545.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "{error.string.empty}")
    @Size(min=3, max=255, message = "{error.size}")
    private String name;
    @NotEmpty(message = "{error.string.empty}")
    @Size(min=10, message = "{error.size.min}")
    @Column(columnDefinition = "TEXT")
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
    private Date createdDate;

    @Transient
    private MultipartFile image;

    @ManyToOne //(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> listReview;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Product() {
        this.photo = "https://oojavascript.blob.core.windows.net/waaimage/noimage.jpg";
    }
}