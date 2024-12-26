package com.CSED.SmartCityParking.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public UserAndDriver createUser(UserAndDriver userAndDriver) {
        String EncryptedPassword = encoder.encode(userAndDriver.getPassword());
        userRepository.createUser(userAndDriver.getUsername() , EncryptedPassword, userAndDriver.getEmail() , userAndDriver.getRole());
        Long currentID = userRepository.getLastInsertId();
        if (userAndDriver.getRole().equals("driver"))
        {
            if (userAndDriver.getLicense() == null)
            {
                deleteUser(currentID);
                throw new IllegalArgumentException ("License is null");
            }
            else
            {
                userRepository.createDriver(currentID , userAndDriver.getLicense());
                return userRepository.findUserAndDriverByID(currentID);
            }
        }

        else return userRepository.findUserAndDriverByID(currentID);
    }


    public List<UserAndDriver> getAllUsers() {
        return userRepository.findAllUsers();
    }


    public UserAndDriver getUserById(Long id) {
        return userRepository.findUserAndDriverByID(id);
    }



    public UserAndDriver updateUser(Long id, UserAndDriver userDetails) {
        userRepository.updateUser(id , userDetails.getUsername(),userDetails.getPassword(), userDetails.getEmail() , userDetails.getRole());
        userRepository.updateDriver(id , userDetails.getLicense());
        return userRepository.findUserAndDriverByID(id);
    }


    public void deleteUser(Long id) {
        userRepository.deleteDriver(id);
        userRepository.deleteUser(id);
    }

}
