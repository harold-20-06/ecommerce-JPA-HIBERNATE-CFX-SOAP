package com.spectrum.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

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

/*
    @ManyToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Pedido> pedidos = new ArrayList<>();
*/
    public Producto(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto() {
    }
}
