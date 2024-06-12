package team.project.drivee.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import team.project.drivee.repo.UserRepository;
import team.project.drivee.service.UserService;

import java.security.Principal;
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@Controller
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;

    public AdminController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", userRepository.findAll());
        return "admin";
    }
}
