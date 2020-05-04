package miu.edu.cs545.domain;

public class Product {
    Integer id;
    String name;

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

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getPoint() {
        return point;
    }

    public String getPhoto() {
        return photo;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Category getCategory() {
        return category;
    }
}
