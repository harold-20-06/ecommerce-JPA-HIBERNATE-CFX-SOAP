package com.spectrum.ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Producto", description = "Producto de la tienda")
public class Producto {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    @Schema(description = "Identificador único del producto", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Column(name = "nombre", nullable = false)
    @Schema(description = "Nombre del producto", example = "Camisa",required = true)
    private String nombre;

    @Column(name = "precio", nullable = false)
    @Schema(description = "Precio del producto", example = "100.00",minimum = "0.00")
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
