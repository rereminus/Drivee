package team.project.drivee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project.drivee.models.User;
import team.project.drivee.models.Vehicle;
import team.project.drivee.repo.UserRepository;
import team.project.drivee.repo.VehicleRepository;

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


    public void addVehicle(Vehicle vehicle, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        Vehicle newVehicle = new Vehicle();
        newVehicle.setRegNo(vehicle.getRegNo());
        newVehicle.setBrand(vehicle.getBrand());
        newVehicle.setColor(vehicle.getColor());
        newVehicle.setHeight(vehicle.getHeight());
        newVehicle.setWidth(vehicle.getWidth());
        newVehicle.setLength(vehicle.getLength());
        newVehicle.setMaxWeight(vehicle.getMaxWeight());
        newVehicle.setUser(user);
        user.setVehicle(newVehicle);
        vehicleRepository.save(newVehicle);
        userRepository.save(user);
    }
}
