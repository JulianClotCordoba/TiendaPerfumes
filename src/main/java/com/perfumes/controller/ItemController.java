package com.perfumes.controller;

import com.perfumes.domain.Item;
import com.perfumes.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public String verItem(@RequestParam Long id, Model model) {
        Item item = itemService.getItem(id);
        model.addAttribute("producto", item);
        model.addAttribute("productosRelacionados", itemService.getItems());
        return "item/item";
    }
}
