package team.project.drivee.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.project.drivee.models.Trip;
import team.project.drivee.repo.TripRepository;
import team.project.drivee.repo.VehicleRepository;
import team.project.drivee.service.TripService;
import team.project.drivee.service.UserService;
import team.project.drivee.service.VehicleService;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {

    private final VehicleRepository vehicleRepository;
    private final VehicleService vehicleService;
    private final TripService tripService;
    private final UserService userService;
    private final TripRepository tripRepository;

    public MainController(VehicleRepository vehicleRepository, VehicleService vehicleService, TripService tripService, UserService userService, TripRepository tripRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleService = vehicleService;
        this.tripService = tripService;
        this.userService = userService;
        this.tripRepository = tripRepository;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/order")
    public String trip(Model model) {
        model.addAttribute("trip", new Trip());
        return "order_page";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/order")
    public String tripStart(@ModelAttribute("trip") Trip trip, Model model, Principal principal, @RequestParam("tariff") String tariff,
                            @RequestParam("pay") String pay) {
        trip.setCreatedTime(OffsetDateTime.now());
        trip.setTarif(tariff);
        System.out.println(tariff);
        Boolean payT;
        payT = Objects.equals(pay, "cash");
        System.out.println(payT);
        trip.setPaymentType(payT);
        trip.setClient(userService.getUserByPrincipal(principal));
        tripService.addTrip(trip);
        return "order_page";
    }

    @PreAuthorize("hasAuthority('ROLE_DRIVER')")
    @GetMapping("/orders")
    public String ordersInfo(Model model) {
        List<Trip> found = tripRepository.findAll();
        return "ordConfirm";
    }

    @PreAuthorize("hasAuthority('ROLE_DRIVER')")
    @PostMapping("/orders")
    public String ordersConfirm(Model model) {

        return "ordConfirm";
    }


//    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
//    @PostMapping("/trip")
//    public String tripAdd(Model model, Trip trip) {
//        trip.setCreatedTime(OffsetDateTime.from(java.time.LocalDateTime.now()));
//        tripService.addTrip(trip);
//        return "trip";
//    }
//
//    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
//    @GetMapping("/trip/find")
//    public String tripFind(Model model) {
//        model.addAttribute("trip", new Trip());
//        return "find_driver";
//    }
//
//    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
//    @PostMapping("/trip/find")
//    public String createTrip(Model model, Principal principal, @RequestParam BigDecimal weight) {
//        List<Vehicle> found = tripService.findDriver(weight);
//        model.addAttribute("foundDrivers", found);
//        for(Vehicle vehicle: found) {
//            System.out.println(vehicle.getRegNo());
//        }
//        return "redirect:/trip/found";
//    }
//
//    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
//    @GetMapping("/trip/found")
//    public String tripFound(Model model, @RequestParam List<Vehicle> vehicles) {
//        model.addAttribute("foundVehicles", vehicles);
//        return "redirect:/trip";
//    }
}
