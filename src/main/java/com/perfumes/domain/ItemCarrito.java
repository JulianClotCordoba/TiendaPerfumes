package com.perfumes.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "item_carrito")
public class ItemCarrito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrito_id", insertable = false, updatable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Item producto;

    private int cantidad;

    public ItemCarrito() {
    }

    public ItemCarrito(Item producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
