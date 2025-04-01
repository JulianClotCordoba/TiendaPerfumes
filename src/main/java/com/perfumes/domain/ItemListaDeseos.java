package com.perfumes.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "item_lista_deseos")
public class ItemListaDeseos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Item producto;

    public ItemListaDeseos() {
    }

    public ItemListaDeseos(Item producto) {
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getProducto() {
        return producto;
    }

    public void setProducto(Item producto) {
        this.producto = producto;
    }

}
