package team.project.drivee.service;

import org.springframework.stereotype.Service;
import team.project.drivee.models.Trip;
import team.project.drivee.models.Vehicle;
import team.project.drivee.repo.TripRepository;
import team.project.drivee.repo.VehicleRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TripService {

    private final VehicleRepository vehicleRepository;
    private final TripRepository tripRepository;
    private final UserService userService;

    public TripService(VehicleRepository vehicleRepository, TripRepository tripRepository, UserService userService) {
        this.vehicleRepository = vehicleRepository;
        this.tripRepository = tripRepository;
        this.userService = userService;
    }

    public void addTrip(Trip trip){
        Trip newTrip = new Trip();
        newTrip.setCreatedTime(trip.getCreatedTime());
        newTrip.setStartTime(trip.getStartTime());
        newTrip.setEndTime(trip.getEndTime());
        newTrip.setClient(trip.getClient());
        newTrip.setPickupLocation(trip.getPickupLocation());
        newTrip.setDropoffLocation(trip.getDropoffLocation());
        newTrip.setComment(trip.getComment());
        newTrip.setTripCost(trip.getTripCost());
        newTrip.setTripStatus("Создан");
        newTrip.setTarif(trip.getTarif());
        newTrip.setPaymentType(trip.getPaymentType());
        tripRepository.save(newTrip);
    }

//    public void confirmTrip(Trip trip){
//        Trip existingTrip = tripRepository.findById(trip.getId()).orElse(null);
//        existingTrip.setDriver(trip.getDriver());
//        existingTrip.setTripStatus("Выполняется");
//        existingTrip.setDriver(trip.getDriver());
//        tripRepository.save(existingTrip);
//    }

}
