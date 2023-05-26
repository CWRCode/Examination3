package com.example.examinationslab3.controller;

import com.example.examinationslab3.model.Item;
import com.example.examinationslab3.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ConcurrentModificationException;
import java.util.List;

@Controller
public class ItemAPI {

    private final ItemService itemService;

    @Autowired
    public ItemAPI(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/newitem")
    String addNewForm(Model m){
        m.addAttribute("item", new Item());
        return "newproduct";
    }

    @PostMapping("/newitem")
    String addNewMember(@Valid Item item, BindingResult br, Model m ) {
        // NEVER trust a client!!!
        if(br.hasErrors()){
            m.addAttribute("producterror", "not correctly done");
        } else {
            itemService.add(item);
        }
        m.addAttribute("item", new Item());
        return "newproduct";
    }

    @GetMapping("/allitems")
    public String itemsUpdate(Model m){

        List<Item> items = itemService.findItems();

        m.addAttribute("allitems", items);

        return "productmanagement";
    }

    @GetMapping("/updateitem")
    public String itemUpdate(@RequestParam long id, Model m){

        Item item = itemService.findItemById(id);

        m.addAttribute("item", item);

        return "productmodifier";
    }

    @GetMapping("/deleteitem")
    public String deleteItem(@RequestParam long id, Model m){
        itemService.deleteItem(id);

        List<Item> items = itemService.findItems();

        m.addAttribute("allitems", items);

        return "productmanagement";
    }

    @GetMapping("/changeitemname")
    public String updateItemName(@RequestParam long id, Model m){

        Item item = itemService.findItemById(id);
        m.addAttribute("item", item);

        return "changeitemname";
    }

    @PostMapping("/changeitemname")
    public String getNewItemName(@RequestParam long id, @RequestParam String newname, Model m){

        Item item = itemService.findItemById(id);
        m.addAttribute("item", item);

        itemService.updateItemName(id, newname);

        return "productmodifier";
    }

    @GetMapping("/changeitemprice")
    public String updateItemPrice(@RequestParam long id, Model m){

        Item item = itemService.findItemById(id);
        m.addAttribute("item", item);

        return "changeitemprice";
    }

    @PostMapping("/changeitemprice")
    public String getNewItemPrice(@RequestParam long id, @RequestParam Double newprice, Model m){

        Item item = itemService.findItemById(id);
        m.addAttribute("item", item);

        itemService.updateItemPrice(id, newprice);

        return "productmodifier";
    }

    @GetMapping("/changeitemcategory")
    public String updateItemCategory(@RequestParam long id, Model m){

        Item item = itemService.findItemById(id);
        m.addAttribute("item", item);

        return "changeitemcategory";
    }

    @PostMapping("/changeitemcategory")
    public String getNewItemCategory(@RequestParam long id, @RequestParam String newcategory, Model m){

        Item item = itemService.findItemById(id);
        m.addAttribute("item", item);

        itemService.updateItemCategory(id, newcategory);

        return "productmodifier";
    }

    @GetMapping("/addcart")
    public String addItemCart(@RequestParam long id, Model m){

        Item item = itemService.findItemById(id);
        m.addAttribute("item", item);

        return "itemtocart";
    }

    @GetMapping("/store")
    public String displayAllStore(Model m){
        List<Item> items = itemService.findItems();
        m.addAttribute("allstoreitems", items);
        return "frontpagestore";
    }

    @PostMapping("/store")
    public String displayCategoryStore(Model m){
        List<Item> items = itemService.findItems();
        m.addAttribute("allstoreitems", items);

        return "frontpagestore";
    }

    @GetMapping("/searchitem")
    public String searchItem(@RequestParam String name, Model m){

        List<Item> items = itemService.findItemByName(name);
        m.addAttribute("allstoreitems", items);

        if(name.equals("")){
            return "redirect:/store";
        }else if(items.size() == 0) {
            m.addAttribute("errormessage", "No items found");
            return "frontpagestore";
        }

        return "frontpagestore";
    }

    @GetMapping("/store/categories")
    public String seeCategories(Model m){

        List<String> categories = itemService.findItemByCategories();
        m.addAttribute("allcategories", categories);


        return "allcategories";
    }

    @GetMapping("/store/categories/items")
    public String seeCategoriesItems(Model m, @RequestParam String category){

        List<Item> categories = itemService.findItemByCategories(category);

        m.addAttribute("allstoreitems", categories);


        return "frontpagestore";
    }




}


