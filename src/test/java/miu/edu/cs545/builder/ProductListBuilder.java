package miu.edu.cs545.builder;

import miu.edu.cs545.domain.Product;

import java.util.Arrays;
import java.util.List;

public class ProductListBuilder {
   public  ProductBuilder productBuilderOne =new ProductBuilder()
           .withId(1);

    public  ProductBuilder productBuilderTwo =new ProductBuilder()
            .withId(2);


    public List<Product> build() {

        Product first = productBuilderOne.build();
        Product second = productBuilderTwo.build();
        return (List<Product>) Arrays.asList(first, second);
    }
}
