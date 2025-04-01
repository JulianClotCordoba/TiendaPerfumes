package com.perfumes.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "lista_deseos")
public class ListaDeseos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "lista_deseos_id")
    private List<ItemListaDeseos> items = new ArrayList<>();

    public void agregarItem(Item producto) {
        ItemListaDeseos item = new ItemListaDeseos(producto);
        items.add(item);
    }

    public void eliminarItem(Long itemId) {
        items.removeIf(item -> item.getId() != null && item.getId().equals(itemId));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemListaDeseos> getItems() {
        return items;
    }

    public void setItems(List<ItemListaDeseos> items) {
        this.items = items;
    }
}
