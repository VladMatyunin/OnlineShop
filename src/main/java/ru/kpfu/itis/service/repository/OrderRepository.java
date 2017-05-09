package ru.kpfu.itis.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.User;

import java.util.List;

/**
 * Created by vladislav on 04.05.17.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findAllByUser(User user);
}
