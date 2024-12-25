package com.CSED.SmartCityParking.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminServices adminServices;


    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminServices.createAdmin(admin));
    }


    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(adminServices.getAllAdmins());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        return ResponseEntity.ok(adminServices.getAdminById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        return ResponseEntity.ok(adminServices.updateAdmin(id, adminDetails));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        adminServices.deleteAdmin(id);
        return ResponseEntity.ok("Admin deleted successfully!");
    }
}

