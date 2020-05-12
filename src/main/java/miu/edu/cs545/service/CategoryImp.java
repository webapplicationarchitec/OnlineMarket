package miu.edu.cs545.service;

import miu.edu.cs545.domain.Category;
import miu.edu.cs545.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImp  implements CategoryService{
    @Autowired
    private  CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getById(Integer id) {

        return categoryRepository.findById(id);

    }
}
