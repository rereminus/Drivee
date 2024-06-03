package team.project.drivee.repo;

import org.springframework.data.repository.CrudRepository;
import team.project.drivee.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findFirstByEmail(String email);
}