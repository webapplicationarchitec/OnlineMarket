package miu.edu.cs545.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    Integer id;
    String name;

    public Product() {
    }

    public Product(Integer id, String name, String description, Double price, Integer point, String photo, ProductStatus status, Integer categoryId, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.point = point;
        this.photo = photo;
        this.status = status;
        this.categoryId = categoryId;
        this.category = category;
    }

    String description;
    Double price;
    Integer point;
    String photo;
    ProductStatus status;
    Integer categoryId;
    Category category;

}
