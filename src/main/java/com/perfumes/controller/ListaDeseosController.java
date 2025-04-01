package com.perfumes.controller;

import com.perfumes.service.ListaDeseosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/listadeseos")
public class ListaDeseosController {

    @Autowired
    private ListaDeseosService listaDeseosService;

    @GetMapping
    public String verListaDeseos(@RequestParam Long listaId, Model model) {
        model.addAttribute("listaDeseos", listaDeseosService.obtenerListaDeseos(listaId));
        return "listadeseos/ver";
    }

    @PostMapping("/agregar")
    public String agregarProducto(@RequestParam Long listaId, @RequestParam Long productoId) {
        listaDeseosService.agregarProductoListaDeseos(listaId, productoId);
        return "redirect:/listadeseos?listaId=" + listaId;
    }

    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestParam Long listaId, @RequestParam Long itemId) {
        listaDeseosService.eliminarProductoListaDeseos(listaId, itemId);
        return "redirect:/listadeseos?listaId=" + listaId;
    }
}
