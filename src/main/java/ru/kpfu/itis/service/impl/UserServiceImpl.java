package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.additional.AuthToken;
import ru.kpfu.itis.model.additional.TransactionInform;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.helper.MyRandomGenerator;
import ru.kpfu.itis.service.repository.AuthTokenRepository;
import ru.kpfu.itis.service.repository.UserRepository;

/**
 * Created by vladislav on 04.05.17.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthTokenRepository authTokenRepository;
    @Autowired
    private MyRandomGenerator generator;
    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public TransactionInform createUser(User user) {
        userRepository.saveAndFlush(user);
        return new TransactionInform(true,"OK");
    }

    @Override
    public TransactionInform updateUser(User user) {
        return createUser(user);
    }

    @Override
    public void confirmUser(User user) {
        user.setAccepted(!user.getAccepted());
        userRepository.saveAndFlush(user);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public TransactionInform deleteUser(User user) {
        userRepository.delete(user);
        return new TransactionInform(true,"OK");
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public TransactionInform deleteUser(Long id) {
        userRepository.delete(id);
        return new TransactionInform(true,"OK");
    }

    @Override
    public void createTokenForUser(User user) {
        AuthToken authToken = new AuthToken();
        authToken.setToken(generator.generateRandomString(15));
        authToken.setUser(user);
        authTokenRepository.saveAndFlush(authToken);
    }
}
