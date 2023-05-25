package com.example.examinationslab3.controller;

import com.example.examinationslab3.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminAPI {

    private final AdminService adminService;

    @Autowired
    public AdminAPI(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin")
    public String authorizeAdmin(@RequestParam String name, @RequestParam String password, Model m){

        Boolean check = adminService.verifyAdmin(name, password);

        if(check) {
            return "adminfrontpage";
        }

        return "adminlogin";
    }

    @GetMapping("/admin")
    public String authorizeMember(Model m){

        adminService.createAdmin();

        m.addAttribute("name", null);
        m.addAttribute("password", null);

        return "adminlogin";
    }


}

