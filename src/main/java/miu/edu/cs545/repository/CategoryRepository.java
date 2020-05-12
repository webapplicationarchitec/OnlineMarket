package miu.edu.cs545.repository;

import miu.edu.cs545.domain.Category;
import miu.edu.cs545.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
