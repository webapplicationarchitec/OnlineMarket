package miu.edu.cs545.service;

import miu.edu.cs545.domain.Category;
import miu.edu.cs545.domain.OnlineOrder;
import miu.edu.cs545.exception.OrderCreateException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public List<Category> getAll();

    public Optional<Category> getById(Integer id);
}
