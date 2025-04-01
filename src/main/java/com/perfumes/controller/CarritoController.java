package com.perfumes.controller;

import com.perfumes.domain.Carrito;
import com.perfumes.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public String verCarrito(@RequestParam Long carritoId, Model model) {
        model.addAttribute("carrito", carritoService.obtenerCarrito(carritoId));
        return "carrito/ver";
    }

    @PostMapping("/agregar")
    public String agregarProducto(@RequestParam Long carritoId,
            @RequestParam Long productoId,
            @RequestParam int cantidad) {
        Carrito carrito = carritoService.agregarProductoAlCarrito(carritoId, productoId, cantidad);
        return "redirect:/carrito?carritoId=" + carrito.getId();
    }

    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestParam Long carritoId, @RequestParam Long itemId) {
        carritoService.eliminarProductoDelCarrito(carritoId, itemId);
        return "redirect:/carrito?carritoId=" + carritoId;
    }
}
