package team.project.drivee.service;

import team.project.drivee.models.User;
import team.project.drivee.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String email, String password){
        if (email == null || password == null) {
            return null;
        } else {
            if (userRepository.findFirstByEmail(email).isPresent()){
                System.out.println("Данный пользователь уже зарегистрирован");
                return null;
            }
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            return userRepository.save(user);
        }
    }

    public User authentication(String email, String password){
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}
