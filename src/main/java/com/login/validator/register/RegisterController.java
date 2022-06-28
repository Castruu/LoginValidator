package com.login.validator.register;

import com.login.validator.user.User;
import com.login.validator.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/" ,"/register"})
    public String displayRegisterPage(Model model) {
        if(userService.isUserLogged()) return "redirect:/home";
        model.addAttribute("userRegister", new User());
        return "register.html";
    }

    @PostMapping({"/registerUser"})
    public String registerUser(@Valid @ModelAttribute("userRegister") User user, Errors errors, Model model) {
        if(errors.hasErrors()) {
            log.error("Register form validation failed due to:  " + errors);
            return "register.html";
        }
        log.info(user.toString());
        if(!userService.registerUserIfNotExists(user)) return "redirect:/login";
        userService.setLoggedUser(user);
        return "redirect:/home";
    }
}
