package com.spectrum.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "Producto")

public class Producto {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private String precio;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "pedido_producto",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "pedido_id")
    )
    private List<Pedido> pedidos = new ArrayList<>();

    public Producto(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto() {
    }
}
