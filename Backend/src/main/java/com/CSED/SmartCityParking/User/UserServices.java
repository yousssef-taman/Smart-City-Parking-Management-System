package com.CSED.SmartCityParking.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.View;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private View error;

    public Object createUser(Separator separator) {
        User user = separator.getUser();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.createUser(user.getUsername() , user.getPassword(), user.getEmail() , user.getRole());
        Long currentID = userRepository.getLastInsertId();
        user.setId(currentID);
        if (user.getRole().equals("driver"))
        {
            if (separator.getLicense() == null)
            {
                deleteUser(currentID);
                throw new IllegalArgumentException ("License is null");
            }
            else
            {
                userRepository.createDriver(currentID , separator.getLicense());
                separator.setLicense(userRepository.findDriverByID(currentID));
                separator.setUser(user);
                return separator;
            }

        }
        else return user;
    }


    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }


    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public String getDriverByID(Long id) {
        return userRepository.findDriverByID(id);
    }


    public User updateUser(Long id, User userDetails) {
        userRepository.updateUser(id , userDetails.getUsername(),userDetails.getPassword(), userDetails.getEmail() , userDetails.getRole());
        return userRepository.findUserById(id);
    }


    public void deleteUser(Long id) {
        userRepository.deleteDriver(id);
        userRepository.deleteUser(id);
    }

}
