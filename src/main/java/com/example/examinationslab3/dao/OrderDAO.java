package com.example.examinationslab3.dao;

import com.example.examinationslab3.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {


}
