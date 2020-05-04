package miu.edu.cs545.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Category {
    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }



    private Integer id;
    private String name;
}
