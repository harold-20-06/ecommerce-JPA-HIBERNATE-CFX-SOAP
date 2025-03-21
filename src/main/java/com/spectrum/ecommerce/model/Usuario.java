package com.spectrum.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Usuario", description = "Usuario de la tienda")
public class Usuario {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    @Schema(description = "Identificador único del usuario", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Column(name = "nombre", nullable = false)
    @Schema(description = "Nombre del usuario", example = "Juan")
    private String nombre;

    @Column(name = "apellido1", nullable = false)
    @Schema(description = "Primer apellido del usuario", example = "Perez")
    private String apellido1;

    @Column(name = "apellido2", nullable = false)
    @Schema(description = "Segundo apellido del usuario", example = "Gomez")
    private String apellido2;

    @Column(name = "email", nullable = false)
    @Schema(description = "Correo electrónico del usuario", example = "juanperezgomez@gmail.com",required = true)
    private String email;

/*  @TODO: evitar conflictos con la serializacion en rest api con jackson analizar implementar DTOs
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();
*/
    public Usuario(String nombre, String apellido1, String apellido2, String email) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;

    }

    public Usuario() {

    }


}
