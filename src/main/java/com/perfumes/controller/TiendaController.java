package com.perfumes.controller;

import com.perfumes.domain.Carrito;
import com.perfumes.domain.ListaDeseos;
import com.perfumes.service.CarritoService;
import com.perfumes.service.ItemService;
import com.perfumes.service.ListaDeseosService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TiendaController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ListaDeseosService listaDeseosService;

    @GetMapping("/tienda")
    public String tienda(
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) Long marca,
            @RequestParam(required = false) Long familia,
            Model model
    ) {

        Carrito carrito = carritoService.obtenerCarrito(1L);
        ListaDeseos listaDeseos = listaDeseosService.obtenerListaDeseos(1L);

        model.addAttribute("productos", itemService.getItemsFiltrados(genero, marca, familia));
        model.addAttribute("generos", itemService.getGeneros());
        model.addAttribute("marcas", itemService.getMarcas());
        model.addAttribute("familias", itemService.getFamilias());
        model.addAttribute("filtros", Map.of(
                "genero", genero != null ? genero : "",
                "marca", marca != null ? marca : "",
                "familia", familia != null ? familia : ""
        ));
        model.addAttribute("cartCount", carrito.getItems().size());
        model.addAttribute("wishlistCount", listaDeseos.getItems().size());

        return "tienda/ver";
    }

}
