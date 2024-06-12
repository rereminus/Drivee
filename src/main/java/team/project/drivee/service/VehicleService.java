package team.project.drivee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.drivee.models.User;
import team.project.drivee.models.Vehicle;
import team.project.drivee.repo.UserRepository;
import team.project.drivee.repo.VehicleRepository;

import java.math.BigDecimal;
import java.security.Principal;


@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, UserRepository userRepository, UserService userService) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    public void addVehicle(Principal principal, String regNo, String brand, String color,
                           BigDecimal length, BigDecimal width, BigDecimal height,
                           BigDecimal maxWeight) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegNo(regNo);
        vehicle.setBrand(brand);
        vehicle.setColor(color);
        vehicle.setLength(length);
        vehicle.setWidth(width);
        vehicle.setHeight(height);
        vehicle.setMaxWeight(maxWeight);
        vehicle.setUser(userService.getUserByPrincipal(principal));
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        User user = userService.getUserByPrincipal(principal);
        user.setVehicle(savedVehicle);
        userRepository.save(user);
    }
}
