package com.app.cfd.controllers;

import com.app.cfd.services.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/getBearer")
    public String getBearer() {
        return adminService.getBearer();
    }

    @PostMapping("/test")
    public void test() {}

    @PostMapping("/getBearer")
    public String postBearer() {
        return adminService.getBearer();
    }
}
