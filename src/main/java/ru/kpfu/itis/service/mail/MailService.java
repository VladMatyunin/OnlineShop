package ru.kpfu.itis.service.mail;

import ru.kpfu.itis.service.exception.UserNotFoundException;

import javax.mail.MessagingException;

/**
 * Created by vladislav on 05.05.17.
 */

public interface MailService {
    void recoverPassword(String email) throws MessagingException, UserNotFoundException;
    void sendConfirmMessage(String email) throws MessagingException;
}
