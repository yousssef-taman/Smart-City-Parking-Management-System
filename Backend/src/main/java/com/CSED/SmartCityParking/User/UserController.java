package com.CSED.SmartCityParking.User;
import com.CSED.SmartCityParking.ParkingLot.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServices userServices;


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserAndDriver userAndDriver) {
        try{
            UserAndDriver NewUser = userServices.createUser(userAndDriver);
            return ResponseEntity.ok(NewUser);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Constraint violation");

        }
        catch (IllegalArgumentException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("License can't be null");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred. Please try again later.");
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserAndDriver>  RetrivedUsers = userServices.getAllUsers();
        if (RetrivedUsers.isEmpty() ) {
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok(RetrivedUsers);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserAndDriver RetrivedUser = userServices.getUserById(id);
         if (RetrivedUser == null) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
         }
         else return ResponseEntity.ok(RetrivedUser);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserAndDriver> updateUser(@PathVariable Long id, @RequestBody UserAndDriver userDetails) {
        return ResponseEntity.ok(userServices.updateUser(id, userDetails));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        UserAndDriver UserDelete = userServices.getUserById(id);
        if (UserDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        else userServices.deleteUser(id);
        return ResponseEntity.ok("User "+id+" deleted successfully!");
    }


    @GetMapping("searchLot")
    public ResponseEntity<List<ParkingLot>> getLots(@Param(value = "location") String location) {
        List<ParkingLot> lots = this.userServices.searchLot(location);
        if (lots == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lots, HttpStatus.OK);
    }
}

