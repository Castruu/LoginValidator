package com.login.validator.login;

import com.login.validator.user.User;
import com.login.validator.user.UserLogin;
import com.login.validator.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/login"})
    public String displayLoginPage(Model model){
        if(userService.isUserLogged()) return "redirect:/home";
        model.addAttribute("userLogin", new UserLogin());
        return "login.html";
    }

    @PostMapping({"/logUser"})
    public String loginUser(@Valid @ModelAttribute("userLogin") UserLogin userLogin, Errors errors, Model model) {
        if(errors.hasErrors()) {
            log.error("Login form validation failed due to: " + errors);
            return "login.html";
        }
        log.info(userLogin.toString());
        User user = userService.loginUserIfExists(userLogin);
        if(null == user) return "redirect:/login";
        userService.setLoggedUser(user);
        return "redirect:/home";
    }
}
