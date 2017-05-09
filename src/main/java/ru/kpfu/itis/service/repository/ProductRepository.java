package ru.kpfu.itis.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Brand;
import ru.kpfu.itis.model.Category;
import ru.kpfu.itis.model.Product;

import java.util.List;

/**
 * Created by vladislav on 04.05.17.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findAllByCategoryAndCostBetweenAndBrandIn(Category category, int minValue, int maxValue, List<Brand> brands);
    List<Product> findAllByCategoryAndCostBetween(Category category, int minValue, int maxValue);
    List<Product> findAllByCategory(Category category);
}
