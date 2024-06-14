package team.project.drivee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.project.drivee.models.Trip;
import team.project.drivee.models.User;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findAllByClient(User user);
    List<Trip> findAllByDriver(User user);
    List<Trip> findAllByTripStatus(String status);

    Trip findById(int id);
}
