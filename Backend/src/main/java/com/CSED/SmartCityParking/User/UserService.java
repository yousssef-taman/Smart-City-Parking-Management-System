package com.CSED.SmartCityParking.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

@Service
public class UserService {


    private final PasswordEncoder passwordEncoder ;
    private final UserDAO userDao;
    private final UserMapper userMapper ;


    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserDAO userDao , UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.userMapper = userMapper ;
    }


    public Integer createUser(User user) {
        try {
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            return this.userDao.addUser(user);
        } catch (DataAccessException dataAccessException) {
            return -1;
        }
    }


    @Secured("ADMIN")
    public List<UserDTO> getAllUsers() {
        try {
            List<User> users = this.userDao.getAllUsers();
            List<UserDTO> userDTOS = users.stream().filter(user -> user.getRole().toString().equals("DRIVER")).map(this.userMapper).toList();
            return userDTOS;
        } catch (DataAccessException dataAccessException ) {
            return null;
        }

    }


    @Transactional
    public Boolean  updateUser(Integer id, Map<String , Object> attributes) {
        try {
            if (attributes.containsKey("username")) {
                this.userDao.updateUsername(id,  (String) attributes.get("username"));
            }
            if (attributes.containsKey("password")) {
                this.userDao.updatePassword(id, passwordEncoder.encode((String) attributes.get("password")));
            }
            if (attributes.containsKey("email")) {
                this.userDao.updateEmail(id, (String) attributes.get("email"));
            }
            return true;
        } catch (DataAccessException dataAccessException) {
            return false;
        }
    }

    public Boolean deleteUser(Integer id) {
        try {
            this.userDao.deleteUser(id);
            return true;
        } catch (DataAccessException dataAccessException) {
            return false;
        }
    }

//    public List<ParkingLot> searchLot(String location) {
//       return  this.parkingLotService.searchLot(location);
//    }

}
