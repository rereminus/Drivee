package team.project.drivee.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.project.drivee.models.Enum.Role;
import team.project.drivee.models.User;
import team.project.drivee.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByPhone(principal.getName());
    }

    public boolean registerUser(@NonNull User user, boolean type){
        if (userRepository.findByPhone(user.getPhone()) != null){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (type){
            user.getRoles().add(Role.ROLE_DRIVER);
        }
        else{
            user.getRoles().add(Role.ROLE_USER);
        }
        userRepository.save(user);
        return true;
    }


    public void updateUser(User user, Principal principal){
        User existingUser = getUserByPrincipal(principal);
        existingUser.setFName(user.getFName());
        existingUser.setMName(user.getMName());
        existingUser.setLName(user.getLName());
        existingUser.setPhone(user.getPhone());
        userRepository.save(existingUser);
    }

}
