package team.project.drivee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import team.project.drivee.models.Enum.Role;
import team.project.drivee.models.User;
import team.project.drivee.repo.UserRepository;
import team.project.drivee.service.UserService;

import java.security.Principal;
import java.util.Arrays;
import java.util.Map;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login_page";
    }

    @PostMapping("/registration")
    public String Register(User user, Model model) {
        if (!userService.registerUser(user)) {
            model.addAttribute("errorMsg", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "register_page";
        }
        return "redirect:/login";
    }

    @GetMapping("/account")
    public String PersonalAccount(Model model, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("roles", Arrays.stream(Role.values()).filter(r -> !r.name().equals("ROLE_ADMIN")).toArray());
        return "personal_page";
    }

    @PostMapping("/account")
    public String updateAccount(Principal principal,@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        //userService.updateUser(user);
        userService.changeUserRole(user, form);
        return "redirect:/account";
    }

//    @PostMapping("/account/role")
//    public String changeRole(@RequestParam("userId") User user, @RequestParam Map<String, String> form){
//        userService.changeUserRole(user, form);
//        return "redirect:/account";
//    }

}
