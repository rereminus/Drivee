package team.project.drivee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import team.project.drivee.models.User;
import team.project.drivee.models.Vehicle;
import team.project.drivee.repo.UserRepository;
import team.project.drivee.repo.VehicleRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

//    public Vehicle addVehicle(@NonNull String regNo, String brand, String color,
//                              BigDecimal length, BigDecimal width, BigDecimal height, BigDecimal maxWeight,
//                              Principal principal) {
//        if (vehicleRepository.findByRegNo(regNo).isPresent()) {
//            System.out.println("Данная машина уже зарегистрирована в системе");
//            return null;
//        }
//        Vehicle vehicle = new Vehicle();
//        vehicle.setUser(getUserByPrincipal(principal));
//        vehicle.setRegNo(regNo);
//        vehicle.setBrand(brand);
//        vehicle.setColor(color);
//        vehicle.setLength(length);
//        vehicle.setWidth(width);
//        vehicle.setHeight(height);
//        vehicle.setMaxWeight(maxWeight);
//
//        return vehicleRepository.save(vehicle);
//    }

    public void addVehicle(Vehicle vehicle, Principal principal) {
        vehicle.setUser(getUserByPrincipal(principal));
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
    }
}
