package team.project.drivee.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.project.drivee.models.Enum.Role;
import team.project.drivee.models.User;
import team.project.drivee.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder/*, UserMapper userMapper*/) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public boolean registerUser(@NonNull User user){
        if (userRepository.findByEmail(user.getEmail()) != null){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        return true;
    }


//    public void updateUser(User user){
//        System.out.println(user.getFName());
//        User existingUser = userRepository.findById(user.getId()).orElse(null);
//        existingUser.setFName(user.getFName());
//        existingUser.setMName(user.getMName());
//        existingUser.setLName(user.getLName());
//        existingUser.setPhone(user.getPhone());
//        userRepository.save(existingUser);
//    }

    public void changeUserRole(User user, Map<String, String> form){
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

}
