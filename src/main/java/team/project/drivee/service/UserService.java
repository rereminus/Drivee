package team.project.drivee.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import team.project.drivee.models.Enum.Role;
import team.project.drivee.models.User;
import team.project.drivee.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

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

//    public User authentication(String email, String password){
//        return userRepository.findByEmailAndPassword(email, password).orElse(null);
//    }
//
//    public User getUserById(String email, String password){
//        return userRepository.findByEmailAndPassword(email, password).orElse(null);
//    }

//    public User refreshUserByFields(int id, Map<String, Object> fields){
//        Optional<User> existingUser = userRepository.findById(id);
//        if (existingUser.isPresent()){
//            fields.forEach((key, value) ->{
//                Field field = ReflectionUtils.findField(User.class, key);
//                field.setAccessible(true);
//                ReflectionUtils.setField(field, existingUser.get(), value);
//            });
//            return userRepository.save(existingUser.get());
//        }
//        return null;
//    }

    public void updateUser(User user) {
        user = User.builder()
                .id(user.getId())
                .fName(user.getFName())
                .mName(user.getMName())
                .lName(user.getLName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .photo(user.getPhoto())
                .numberOfMovers(user.getNumberOfMovers())
                .vehicle(user.getVehicle())
                .typeAcc(user.getTypeAcc())
                .build();
        userRepository.save(user);
    }

}
