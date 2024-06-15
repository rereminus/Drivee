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
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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
    public String tripStart(@ModelAttribute("trip") Trip trip, Principal principal,
                            @RequestParam(value="dateTime", required = false) String dateTime,
                            @RequestParam(value="dateTimeInput", required = false) LocalDateTime dateTimeInput) {
        trip.setCreatedTime(OffsetDateTime.now());
        if (!Objects.equals(dateTime, "dateTime") || dateTimeInput == null) {
            trip.setStartTime(OffsetDateTime.now());
        }
        else{
            ZoneId zoneId = ZoneId.systemDefault();
            trip.setStartTime(dateTimeInput.atZone(zoneId).toOffsetDateTime());
        }
        trip.setClient(userService.getUserByPrincipal(principal));
        tripService.addTrip(trip);
        return "order_page";
    }

    @PreAuthorize("hasAuthority('ROLE_DRIVER')")
    @GetMapping("/orders")
    public String ordersInfo(Model model) {
        List<Trip> foundOrders = tripRepository.findAllByTripStatus("Создан");
        model.addAttribute("foundOrders", foundOrders);
        return "ordConfirm";
    }

//    @PreAuthorize("hasAuthority('ROLE_DRIVER')")
//    @PostMapping("/orders")
//    public String ordersConfirm(Model model, @ModelAttribute("trip") Trip trip, Principal principal) {
//        trip.setDriver(userService.getUserByPrincipal(principal));
//        tripService.confirmTrip(trip);
//        return "ordConfirm";
//    }
}
