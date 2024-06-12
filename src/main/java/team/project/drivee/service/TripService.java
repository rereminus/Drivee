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

    public TripService(VehicleRepository vehicleRepository, TripRepository tripRepository) {
        this.vehicleRepository = vehicleRepository;
        this.tripRepository = tripRepository;
    }

    public List<Vehicle> findDriver(BigDecimal weight){
        return  vehicleRepository.findAllByMaxWeightLessThanEqual(weight);
    }

    public void addTrip(Trip trip){
        tripRepository.save(trip);
    }


}
