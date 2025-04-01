package com.perfumes.service;

import com.perfumes.domain.Carrito;
import com.perfumes.domain.Item;
import com.perfumes.domain.ItemCarrito;
import com.perfumes.repository.CarritoRepository;
import com.perfumes.repository.ItemCarritoRepository;
import com.perfumes.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemCarritoRepository itemCarritoRepository;

    @Transactional
    public Carrito agregarProductoAlCarrito(Long carritoId, Long productoId, int cantidad) {
        Carrito carrito = carritoRepository.findById(carritoId).orElse(null);
        if (carrito == null) {
            carrito = new Carrito();
            carrito = carritoRepository.save(carrito);
        }
        Item producto = itemRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ItemCarrito item = new ItemCarrito(producto, cantidad);
        carrito.getItems().add(item);
        itemCarritoRepository.save(item);

        return carritoRepository.save(carrito);
    }

    @Transactional
    public void eliminarProductoDelCarrito(Long carritoId, Long itemId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        ItemCarrito itemAEliminar = carrito.getItems()
                .stream()
                .filter(item -> item.getId() != null && item.getId().equals(itemId))
                .findFirst()
                .orElse(null);

        if (itemAEliminar != null) {
            carrito.getItems().remove(itemAEliminar);
            itemCarritoRepository.delete(itemAEliminar);
        }

        carritoRepository.save(carrito);
    }

    @Transactional(readOnly = true)
    public Carrito obtenerCarrito(Long carritoId) {
        return obtenerOCrearCarrito(carritoId);
    }

    @Transactional
    public Carrito obtenerOCrearCarrito(Long carritoId) {
        return carritoRepository.findById(carritoId)
                .orElseGet(() -> {
                    Carrito nuevoCarrito = new Carrito();
                    nuevoCarrito.setId(carritoId);
                    return carritoRepository.save(nuevoCarrito);
                });
    }
}
