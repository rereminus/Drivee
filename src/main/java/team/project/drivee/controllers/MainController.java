package team.project.drivee.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.project.drivee.models.Trip;
import team.project.drivee.models.Vehicle;
import team.project.drivee.repo.VehicleRepository;
import team.project.drivee.service.TripService;
import team.project.drivee.service.VehicleService;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.OffsetDateTime;
import java.util.List;

@Controller
public class MainController {

    private final VehicleRepository vehicleRepository;
    private final VehicleService vehicleService;
    private final TripService tripService;

    public MainController(VehicleRepository vehicleRepository, VehicleService vehicleService, TripService tripService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleService = vehicleService;
        this.tripService = tripService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/trip")
    public String trip(Model model) {
        model.addAttribute("trip", new Trip());
        return "trip";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/trip")
    public String tripAdd(Model model, Trip trip) {
        trip.setCreatedTime(OffsetDateTime.from(java.time.LocalDateTime.now()));
        tripService.addTrip(trip);
        return "trip";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/trip/find")
    public String tripFind(Model model) {
        model.addAttribute("trip", new Trip());
        return "find_driver";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/trip/find")
    public String createTrip(Model model, Principal principal, @RequestParam BigDecimal weight) {
        List<Vehicle> found = tripService.findDriver(weight);
        model.addAttribute("foundDrivers", found);
        for(Vehicle vehicle: found) {
            System.out.println(vehicle.getRegNo());
        }
        return "redirect:/trip/found";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("trip/found")
    public String tripFound(Model model, @RequestParam List<Vehicle> vehicles) {
        model.addAttribute("foundVehicles", vehicles);
        return "redirect:/trip";
    }

}
