package ru.kpfu.itis.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.service.exception.UserNotFoundException;
import ru.kpfu.itis.service.helper.MyRandomGenerator;
import ru.kpfu.itis.service.repository.AuthTokenRepository;
import ru.kpfu.itis.service.repository.UserRepository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by vladislav on 05.05.17.
 */
@Component
@PropertySource("classpath:app.properties")
public class MailServiceImpl implements MailService {
    final
    Environment env;
    final
    UserRepository userRepository;
    @Autowired
    MyRandomGenerator generator;
    @Autowired
    AuthTokenRepository tokenRepository;
    private String FROM;
    private String PASSWORD;
    private final String HOST = "localhost";
    @Autowired
    public MailServiceImpl(Environment env, UserRepository userRepository){
        this.env = env;
        FROM = env.getRequiredProperty("email.author");
        PASSWORD = env.getRequiredProperty("email.password");
        this.userRepository = userRepository;
    }
    @Override
    public void recoverPassword(String email) throws MessagingException, UserNotFoundException {
        //TODO
    }

    @Override
    public void sendConfirmMessage(String email) throws MessagingException {
        String stringBuilder = "Для подтвержения аккаунта перейдите по ссылке: " +
                "localhost:8080/confirm_user?token=" +
                tokenRepository.findByUser(userRepository.findByEmail(email)).getToken();
        buildMail(email,"Подтверждение аккаунта", stringBuilder);

    }
    private void buildMail(String email, String title, String text) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM, PASSWORD);
                    }
                });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM));
        System.out.println(email);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject(title);
        message.setText(text);
        Transport.send(message);
    }
}
