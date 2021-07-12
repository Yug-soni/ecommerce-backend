package com.yug.startup.controller;

import com.yug.startup.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUserController {

    private final AdminUserService adminUserService;
    private final String defaultEmail = "defaultEmail";
    private final String defaultPassword = "defaultPassword";

    public AdminUserController(@Autowired AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/insert-default-admin-user")
    public String insertAdminUser() {
        boolean isSuccess = adminUserService.insertAdminUser(this.defaultEmail, this.defaultPassword);

        return isSuccess ? "Default admin user inserted successfully." : "Error inserting the admin user.";
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
