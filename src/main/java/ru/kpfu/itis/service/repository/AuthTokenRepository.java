package ru.kpfu.itis.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.additional.AuthToken;

/**
 * Created by vladislav on 07.05.17.
 */
@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long>{
    AuthToken findByUser(User user);
    AuthToken findByToken(String token);
}
