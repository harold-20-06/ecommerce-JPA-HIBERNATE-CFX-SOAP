package com.spectrum.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "Pedido")
public class Pedido {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

//    @Column(name = "usuarioId", nullable = false)
//    private String usuarioId;

//    @Column(name = "productoId", nullable = false)
//    private String productoId;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Usuario usuario;

    @ToString.Exclude
    @ManyToMany(mappedBy = "pedidos",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Producto> productos = new ArrayList<>();

    @Column(name = "cantidad", nullable = false)
    private String cantidad;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    public Pedido(Usuario usuario, List<Producto> productos, String cantidad, Date fecha) {
        this.usuario = usuario;
        this.productos = productos;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Pedido() {
    }

}
