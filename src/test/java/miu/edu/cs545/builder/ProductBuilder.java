package miu.edu.cs545.builder;

import miu.edu.cs545.domain.Product;

public class ProductBuilder {
    private Product product;

    public ProductBuilder() {
        this.product = new Product();
    }

    public ProductBuilder withId(Integer id) {
        this.product.setId(id);
        return this;
    }
    public Product build() {
        return product;
    }
}
