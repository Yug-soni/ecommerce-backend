package com.yug.startup.controller;

import com.yug.startup.model.AdminUser;
import com.yug.startup.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
public class AdminUserController {

    private final AdminUserService adminUserService;
    private final String defaultEmail = "defaultEmail";
    private final String defaultPassword = "defaultPassword";

    public AdminUserController(@Autowired AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/")
    public String home() {
        this.adminUserService.getAdminUserList();
        return "Work done successfully";
    }

    @GetMapping("/insert-default-admin-user")
    public ResponseEntity<AdminUser> insertAdminUser() {
        boolean isSuccess = adminUserService.insertAdminUser(this.defaultEmail, this.defaultPassword);

        AdminUser adminUser = new AdminUser();
        adminUser.setAdminUserId(0L);
        adminUser.setEmail("email");
        adminUser.setPassword("password");

        return isSuccess ?
                new ResponseEntity<>( adminUser, HttpStatus.OK) :
                new ResponseEntity<>(adminUser, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/update-default-admin-user")
    public String updateAdminUser() {
        String newEmail = "newEmail";
        String newPassword = "newPassword";
        boolean isSuccess = this.adminUserService.updateAdminUser(newEmail, newPassword, this.defaultEmail, this.defaultPassword);

        return isSuccess ? "Default admin user updated successfully." : "Error updating the admin user";
    }

    @GetMapping("/delete-default-admin-user")
    public String deleteAdminUser() {
        boolean isSuccess = this.adminUserService.deleteAdminUser(defaultEmail, defaultPassword);
        return isSuccess ? "Default admin user deleted successfully." : "Error deleting the admin user.";
    }
}
