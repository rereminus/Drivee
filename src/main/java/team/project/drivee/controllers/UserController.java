package team.project.drivee.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import team.project.drivee.models.Enum.Role;
import team.project.drivee.models.User;
import team.project.drivee.repo.UserRepository;
import team.project.drivee.service.UserService;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    public String Register(@ModelAttribute User user, Model model) {
        if (!userService.registerUser(user)) {
            model.addAttribute("errorMsg", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "register_page";
        }
        return "redirect:/login";
    }
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/account/{user}")
    public String getPersonalAccount(Model model, Principal principal,@PathVariable("user") User user) {
        model.addAttribute("user", user);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        //model.addAttribute("roles", Role.values());
        //model.addAttribute("typeAcc", user.getTypeAcc());
        return "personal_page";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/account/{user}/edit")
    public String getPersonalAccountEdit(Model model, Principal principal, @PathVariable("user") User user) {
        model.addAttribute("user", user);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        return "personal_page_edit";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/account/edit")
    public String updateAccount(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/personal_page_edit";
    }

}
