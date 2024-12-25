package com.CSED.SmartCityParking.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServices {

    @Autowired
    private AdminRepository adminRepository;


    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }


    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }


    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found!"));
    }


    public Admin updateAdmin(Long id, Admin adminDetails) {
        Admin existingAdmin = getAdminById(id);
        existingAdmin.setUsername(adminDetails.getUsername());
        existingAdmin.setPassword(adminDetails.getPassword());
        existingAdmin.setEmail(adminDetails.getEmail());
        return adminRepository.save(existingAdmin);
    }


    public void deleteAdmin(Long id) {
        Admin admin = getAdminById(id);
        adminRepository.delete(admin);
    }
}
