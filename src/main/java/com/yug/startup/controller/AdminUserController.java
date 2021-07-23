package com.yug.startup.controller;

import com.yug.startup.model.AdminUser;
import com.yug.startup.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/special/")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(@Autowired AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<AdminUser>> home() {
        List<AdminUser> adminUserList = this.adminUserService.getAdminUserList();
        return adminUserList != null ?
                new ResponseEntity<>(adminUserList, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                ;
    }

    @PostMapping("/insert")
    public ResponseEntity<AdminUser> insertAdminUser(@RequestBody AdminUser adminUser) {
        AdminUser arg = this.changeMe(adminUser);
        boolean isSuccess = adminUserService.insertAdminUser(arg);
        return isSuccess ?
                new ResponseEntity<>(arg, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public ResponseEntity<AdminUser> updateAdminUser(@RequestBody AdminUser adminUser) {
        AdminUser arg = this.changeMe(adminUser);
        boolean isSuccess = this.adminUserService.updateAdminUser(arg);

        return isSuccess ?
                new ResponseEntity<>(arg, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                ;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<AdminUser> deleteAdminUser(@RequestBody AdminUser adminUser) {
        AdminUser arg = this.changeMe(adminUser);
        boolean isSuccess = arg != null && this.adminUserService.deleteAdminUser(arg);
        return isSuccess ?
                new ResponseEntity<>(arg, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
                ;
    }

    public AdminUser changeMe(AdminUser adminUser) {
        AdminUser arg = new AdminUser();
        arg.setId(adminUser.getId());
        arg.setEmail(adminUser.getEmail());
        arg.setPassword(adminUser.getPassword());

        return arg;
    }
}
