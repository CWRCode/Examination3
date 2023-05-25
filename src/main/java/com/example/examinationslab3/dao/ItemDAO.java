package com.example.examinationslab3.dao;

import com.example.examinationslab3.model.Admin;
import com.example.examinationslab3.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDAO extends JpaRepository<Item, Long> {

    List<Item> findByName(String itemName);

}
