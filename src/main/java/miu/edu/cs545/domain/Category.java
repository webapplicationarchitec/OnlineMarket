package miu.edu.cs545.domain;

public class Category {
    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    Integer id;

    public String getName() {
        return name;
    }
    String name;
}
