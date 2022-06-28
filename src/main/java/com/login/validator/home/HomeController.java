package com.login.validator.home;

import com.login.validator.user.User;
import com.login.validator.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/home")
    public String displayHomePage(Model model) {
        model.addAttribute("user", userService.getLoggedUser());
        return "home.html";
    }

    @RequestMapping("/logOut")
    public String logOut(Model model) {
        userService.setLoggedUser(null);
        model.addAttribute("user", null);
        return "redirect:/login";
    }
}
