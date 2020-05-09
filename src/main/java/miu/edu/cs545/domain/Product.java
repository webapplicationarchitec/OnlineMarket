package miu.edu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    Integer id;
    String name;
    String description;
    Double price;
    Integer point;
    String photo;
    ProductStatus status;
    Integer categoryId;
    Category category;
}
