package ru.kpfu.itis.service;

import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.additional.TransactionInform;

/**
 * Created by vladislav on 04.05.17.
 */
public interface UserService {
    User getByEmail(String email);
    TransactionInform createUser(User user);
    TransactionInform updateUser(User user);
    void confirmUser(User user);
    TransactionInform deleteUser(User user);
    TransactionInform deleteUser(Long id);
    void createTokenForUser(User user);
}
