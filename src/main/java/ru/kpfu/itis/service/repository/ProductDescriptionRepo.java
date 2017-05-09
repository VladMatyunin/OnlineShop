package ru.kpfu.itis.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.ProductDescription;

/**
 * Created by vladislav on 04.05.17.
 */
@Repository
public interface ProductDescriptionRepo extends JpaRepository<ProductDescription, Long> {
}
