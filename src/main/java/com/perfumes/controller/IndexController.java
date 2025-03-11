package com.perfumes.controller;

import com.perfumes.service.ItemService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String index(
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) Long marca,
            @RequestParam(required = false) Long familia,
            Model model
    ) {
        model.addAttribute("productos", itemService.getItemsFiltrados(genero, marca, familia));
        model.addAttribute("generos", itemService.getGeneros());
        model.addAttribute("marcas", itemService.getMarcas());
        model.addAttribute("familias", itemService.getFamilias());
        model.addAttribute("filtros", Map.of(
                "genero", genero != null ? genero : "",
                "marca", marca != null ? marca : "",
                "familia", familia != null ? familia : ""
        ));
        return "index";
    }

}
