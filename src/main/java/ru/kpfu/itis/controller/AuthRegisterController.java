package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.additional.AuthToken;
import ru.kpfu.itis.model.additional.UserRole;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.mail.MailService;
import ru.kpfu.itis.service.repository.AuthTokenRepository;
import ru.kpfu.itis.service.repository.UserRepository;

import javax.mail.MessagingException;
import javax.validation.Valid;

/**
 * Created by vladislav on 04.05.17.
 */
@Controller
public class AuthRegisterController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private AuthTokenRepository tokenRepository;
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String loadAuthPage(){
        return "auth";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String loadRegPage(ModelMap modelMap){
        modelMap.addAttribute("regForm", new User());
        return "register";
    }
    @RequestMapping(value = "/register", method=RequestMethod.POST)
    public String register(@Valid @ModelAttribute("regForm") User user, BindingResult bindingResult, ModelMap modelMap){
        if (bindingResult.hasErrors()){
            return "register";
        }
        if (!user.getConfirmPassword().equals(user.getPassword())){
            modelMap.addAttribute("passwordError","Пароли не совпадают");
            return "register";
        }
        if (userRepository.findByEmail(user.getEmail())!=null){
            modelMap.addAttribute("emailExists","Пользователь с такой почтой уже зарегистрирован");
            return "register";
        }
        user.setUserRole(UserRole.ROLE_USER);
        userService.createTokenForUser(userRepository.saveAndFlush(user));
        try {
            mailService.sendConfirmMessage(user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "redirect:/catalog/1";
    }
    @RequestMapping(value = "/confirm_user", method = RequestMethod.GET)
    public String confirmUser(@RequestParam(value = "token") String token){
        AuthToken authToken = tokenRepository.findByToken(token);
        userService.confirmUser(authToken.getUser());
        tokenRepository.delete(authToken);
        return "redirect:/catalog/1";
    }
    @RequestMapping(value = "/main")
    public String loadMainPage(){
        return "redirect:/catalog/1";
    }

}
