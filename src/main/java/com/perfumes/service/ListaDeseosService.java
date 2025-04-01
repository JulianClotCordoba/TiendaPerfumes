package com.perfumes.service;

import com.perfumes.domain.Item;
import com.perfumes.domain.ItemListaDeseos;
import com.perfumes.domain.ListaDeseos;
import com.perfumes.repository.ItemListaDeseosRepository;
import com.perfumes.repository.ItemRepository;
import com.perfumes.repository.ListaDeseosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ListaDeseosService {

    @Autowired
    private ListaDeseosRepository listaDeseosRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemListaDeseosRepository itemListaDeseosRepository;

    @Transactional
    public ListaDeseos agregarProductoListaDeseos(Long listaId, Long productoId) {
        ListaDeseos lista = listaDeseosRepository.findById(listaId).orElseGet(() -> {
            ListaDeseos nuevaLista = new ListaDeseos();
            nuevaLista.setId(listaId);
            return listaDeseosRepository.save(nuevaLista);
        });

        Item producto = itemRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ItemListaDeseos item = new ItemListaDeseos(producto);
        lista.getItems().add(item);
        itemListaDeseosRepository.save(item);

        return listaDeseosRepository.save(lista);
    }

    @Transactional
    public void eliminarProductoListaDeseos(Long listaId, Long itemId) {
        ListaDeseos lista = listaDeseosRepository.findById(listaId)
                .orElseThrow(() -> new RuntimeException("Lista de deseos no encontrada"));

        ItemListaDeseos itemAEliminar = lista.getItems()
                .stream()
                .filter(item -> item.getId() != null && item.getId().equals(itemId))
                .findFirst()
                .orElse(null);

        if (itemAEliminar != null) {
            lista.getItems().remove(itemAEliminar);
            itemListaDeseosRepository.delete(itemAEliminar);
        }

        listaDeseosRepository.save(lista);
    }

    @Transactional(readOnly = true)
    public ListaDeseos obtenerListaDeseos(Long listaId) {
        return listaDeseosRepository.findById(listaId)
                .orElseGet(() -> {
                    ListaDeseos nuevaLista = new ListaDeseos();
                    nuevaLista.setId(listaId);
                    return listaDeseosRepository.save(nuevaLista);
                });
    }
}
