package com.example.examinationslab3.dao;

import com.example.examinationslab3.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDAO extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsername(String username);

}
