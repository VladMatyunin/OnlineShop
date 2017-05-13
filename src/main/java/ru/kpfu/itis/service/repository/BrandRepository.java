package ru.kpfu.itis.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Brand;

/**
 * Created by vladislav on 07.05.17.
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{
    Brand findByName(String name);
}
