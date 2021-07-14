package com.yug.startup.controller;

import com.yug.startup.model.AdminUser;
import com.yug.startup.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class AdminUserController {

    private final AdminUserService adminUserService;
    private final String defaultEmail = "defaultEmail";
    private final String defaultPassword = "defaultPassword";

    public AdminUserController(@Autowired AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AdminUser>> home() {
        List<AdminUser> adminUserList = this.adminUserService.getAdminUserList();
        return adminUserList != null ?
                new ResponseEntity<>(adminUserList, HttpStatus.OK) :
                new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK)
                ;
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

    @DeleteMapping("/delete-default-admin-user")
    public ResponseEntity<List<AdminUser>> deleteAdminUser(@RequestBody AdminUser adminUser) {
        List<AdminUser> deletedAdminUser = this.adminUserService.deleteAdminUser(adminUser);
        System.out.println(deletedAdminUser);
        return deletedAdminUser != null ?
                new ResponseEntity<>(deletedAdminUser, HttpStatus.OK) :
                new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST)
        ;
    }
}
