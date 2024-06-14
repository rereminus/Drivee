package team.project.drivee.controllers;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.project.drivee.models.User;
import team.project.drivee.models.Vehicle;
import team.project.drivee.repo.UserRepository;
import team.project.drivee.repo.VehicleRepository;
import team.project.drivee.service.UserService;
import team.project.drivee.service.VehicleService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;

@Controller
public class VehicleController {

    private final VehicleService vehicleService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final VehicleRepository vehicleRepository;

    public VehicleController(VehicleService vehicleService, UserRepository userRepository, UserService userService, VehicleRepository vehicleRepository) {
        this.vehicleService = vehicleService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.vehicleRepository = vehicleRepository;
    }

    @PreAuthorize("hasAuthority('ROLE_DRIVER')")
    @GetMapping("/vehicle")
    public String getVehicleInfo(Model model, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        if (vehicleRepository.findByUser(user) != null) {
            model.addAttribute("vehicle", vehicleRepository.findByUser(user));
        }
        else{
            model.addAttribute("vehicle", new Vehicle());
        }
        return "vehicle";
    }

    @PreAuthorize("hasAuthority('ROLE_DRIVER')")
    @PostMapping("/vehicle")
    public String addVehiclePost(Vehicle vehicle, Model model, Principal principal) {
        if (vehicleRepository.findByRegNo(vehicle.getRegNo()).isPresent()){
            model.addAttribute("errorMsg", "Данный автомобиль уже зарегистрирован в системе");
            return "redirect:/account";
        }
        vehicleService.addVehicle(vehicle, principal);
        return "redirect:/order";
    }

}
