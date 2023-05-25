package com.example.examinationslab3.service;

import com.example.examinationslab3.dao.ItemDAO;
import com.example.examinationslab3.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemDAO itemDAO;

    private String nameHolder;


    @Autowired
    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public void add(Item item){

        nameHolder = FirstLetterToUpper(item.getName());
        item.setName(nameHolder);

        nameHolder = FirstLetterToUpper(item.getCategory());
        item.setCategory(nameHolder);

        itemDAO.save(item);


    }

    private static String FirstLetterToUpper(String name) {
        char firstChar = name.toUpperCase().charAt(0);
        String remainingString = name.substring(1).toLowerCase();

        return firstChar + remainingString;
    }

    public Item findItemById(Long id){
        return itemDAO.findById(id).orElseThrow(NullPointerException::new);
    }

    public List<Item> findItemByName(String name){
        if(!name.isEmpty()) {
            FirstLetterToUpper(name);
        }

        return itemDAO.findByName(name);
    }

    public void deleteItem(Long id){
        if(itemDAO.existsById(id)){
            itemDAO.delete(findItemById(id));
        }
    }

    public List<Item> findItems(){
        return itemDAO.findAll();
    }

    public void updateItemName(Long id, String newName){
        Item item = findItemById(id);
        nameHolder = FirstLetterToUpper(newName);
        item.setName(nameHolder);

        itemDAO.save(item);
    }

    public void updateItemPrice(Long id, Double newPrice){
        Item item = findItemById(id);
        item.setPrice(newPrice);
        itemDAO.save(item);
    }

    public void updateItemCategory(Long id, String newCategory){
        Item item = findItemById(id);
        nameHolder = FirstLetterToUpper(newCategory);
        item.setCategory(nameHolder);

        itemDAO.save(item);
    }
}
