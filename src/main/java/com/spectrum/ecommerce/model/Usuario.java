package com.spectrum.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido1", nullable = false)
    private String apellido1;

    @Column(name = "apellido2", nullable = false)
    private String apellido2;

    @Column(name = "email", nullable = false)
    private String email;


    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pedido> pedidos = new ArrayList<>();


    public Usuario(String nombre, String apellido1, String apellido2, String email) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;

    }

    public Usuario() {

    }


}
