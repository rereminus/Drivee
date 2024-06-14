package team.project.drivee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import team.project.drivee.models.Enum.Role;
import team.project.drivee.models.User;
import team.project.drivee.models.Vehicle;
import team.project.drivee.repo.UserRepository;
import team.project.drivee.repo.VehicleRepository;
import team.project.drivee.service.UserService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    public UserController(UserService userService, UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "reg";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "reg";
    }

    @PostMapping("/registration")
    public String Register(User user, @RequestParam(value = "checkV",defaultValue = "false") boolean chekV) {
        if (!userService.registerUser(user, chekV)) {
            return "reg";
        }
        return "redirect:/login";
    }

    @GetMapping("/account")
    public String PersonalAccount(Model model, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        if (user.getRoles().contains(Role.ROLE_USER)) {
            return "lkU";
        }
        else if (user.getRoles().contains(Role.ROLE_DRIVER)) {
            Vehicle vehicle = vehicleRepository.findByUser(user);
            if (vehicle != null) {
                model.addAttribute("vehicle", vehicle);
                BigDecimal capacity = vehicle.getHeight().multiply(vehicle.getLength().multiply(vehicle.getWidth()));
                model.addAttribute("capacity", capacity);
            }
            return "lkV";
        }
        else return "redirect:/admin";
    }

    @PostMapping("/account")
    public String updateAccount(Principal principal, User user) {
        userService.updateUser(user, principal);
        return "redirect:/account";
    }

}
