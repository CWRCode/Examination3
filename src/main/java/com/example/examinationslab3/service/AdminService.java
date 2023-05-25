package com.example.examinationslab3.service;

import com.example.examinationslab3.dao.AdminDAO;
import com.example.examinationslab3.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminDAO adminDAO;


    @Autowired
    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }


    public Boolean verifyAdmin(String name, String password) {
        Admin admin = findAdminById(name);

        if(admin != null && admin.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public Admin findAdminById(String id){

        Optional<Admin> admin = adminDAO.findByUsername(id);
        return admin.orElse(null);

        //return memberDAO.findById(id).orElseThrow(NullPointerException::new);
    }

    public void createAdmin(){
        List<Admin> admins = adminDAO.findAll();
        if(admins.size() == 0) {
            Admin admin = new Admin();
            adminDAO.save(admin);
        }
    }



}
