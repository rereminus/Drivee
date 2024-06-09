package team.project.drivee.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.project.drivee.models.Vehicle;

import java.util.Optional;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    Optional<Vehicle> findById(int id);
    Optional<Vehicle> findByRegNo(String regNo);
}
