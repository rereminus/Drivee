package team.project.drivee.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.project.drivee.models.Vehicle;
import team.project.drivee.repo.UserRepository;
import team.project.drivee.service.VehicleService;

@Controller
public class VehicleController {

    private final VehicleService vehicleService;
    private final UserRepository userRepository;

    public VehicleController(VehicleService vehicleService, UserRepository userRepository) {
        this.vehicleService = vehicleService;
        this.userRepository = userRepository;
    }

    @GetMapping("/account/vehicle")
    public String getDriverAcc(@RequestParam(value="typeAcc", required = true) Boolean typeAcc,
                               Model model, Vehicle vehicle) {;
        model.addAttribute("user_typeAcc", typeAcc);
        model.addAttribute("vehicle", vehicle);
        return "vehicle";
    }

    @PostMapping
    public String postDriverInfo(@ModelAttribute("vehicle") Vehicle vehicle) {

        return "redirect:/account/vehicle";
    }

}
