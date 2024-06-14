package team.project.drivee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.project.drivee.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByEmail(String email);
    User findByPhone(String phone);
}