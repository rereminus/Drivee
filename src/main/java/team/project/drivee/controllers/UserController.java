package team.project.drivee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import team.project.drivee.models.User;
import team.project.drivee.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new User());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new User());
        return "login_page";
    }

    @PostMapping("/register")
    public String Register(@ModelAttribute User user) {
        //System.out.println("register request: "+ user)
        User registeredUser = userService.registerUser(user.getEmail(), user.getPassword());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String Login(@ModelAttribute User user, Model model) {
        //System.out.println("login request: "+ user);
        User authenticatedUser = userService.authentication(user.getEmail(), user.getPassword());
        if (authenticatedUser != null){
            model.addAttribute("userEmail", authenticatedUser.getEmail());
            return "personal_page";
        }
        else{
            return "error_page";
        }
    }

    @GetMapping("/personal_page")
    public String getPersonalAccount(Model model) {
        return "personal_page";
    }

//    @PatchMapping("/personal_page")
//    public String refreshAccount(@ModelAttribute User user, Model model) {
//        User authenticatedUser = userService.authentication(user.getEmail(), user.getPassword());
//
//    }

}
