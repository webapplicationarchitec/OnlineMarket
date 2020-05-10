package miu.edu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Name is not empty")
    @Size(min = 3,message = "Name is more than 2 characters")
    private String name;
    private String description;
    private Double price;
    private Integer point;
    private String photo;
    private ProductStatus status;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Category category;
}