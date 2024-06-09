package team.project.drivee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import team.project.drivee.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //Optional<User> findByEmailAndPassword(String email, String password);
    //Optional<User> findFirstByEmail(String email);
    User findFirstById(int id);
    User findByEmail(String email);
}