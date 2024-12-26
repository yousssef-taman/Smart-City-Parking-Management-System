package com.CSED.SmartCityParking.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServices userServices;



    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody Separator separator) {
        try{
            Object NewUser = userServices.createUser(separator);
            return ResponseEntity.ok(NewUser);
        } catch (DataIntegrityViolationException e) {
            // Handle cases like unique constraint violations (e.g., duplicate username)
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Username or email already exists. Please choose a different one.");

        }
        catch (IllegalArgumentException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("License cann't be null");
        }
        catch (Exception e) {
            // General fallback for other unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred. Please try again later.");
        }
    }


    @GetMapping
    public Object getAllUsers() {
        List<User>  RetrivedUsers = new ArrayList<>(userServices.getAllUsers());
        if (RetrivedUsers.isEmpty() ) {
            return ResponseEntity.noContent().build();
        }else
            return RetrivedUsers;
    }


    @GetMapping("/{id}")
    public Object getUserById(@PathVariable Long id) {
         User RetrivedUser = userServices.getUserById(id);
         if (RetrivedUser == null) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
         }
         else if(RetrivedUser.getRole().equals("driver"))
         {
             Separator separator = new Separator();
             separator.setUser(RetrivedUser);
             separator.setLicense(userServices.getDriverByID(RetrivedUser.getId()));
             return ResponseEntity.ok(separator);
         }
         else return ResponseEntity.ok(RetrivedUser);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return ResponseEntity.ok(userServices.updateUser(id, userDetails));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        User newUser = userServices.getUserById(id);
        if (newUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        else userServices.deleteUser(id);
        return ResponseEntity.ok("User "+id+" deleted successfully!");
    }
}

