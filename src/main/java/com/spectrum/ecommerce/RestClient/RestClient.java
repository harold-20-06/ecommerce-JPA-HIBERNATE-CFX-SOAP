package com.spectrum.ecommerce.RestClient;

import com.spectrum.ecommerce.model.Usuario;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

public class RestClient {

    private static final String BASE_URI = "http://localhost:8081/ecommerce/api/usuarios"; // Reemplaza con tu URL

    public static void main(String[] args) {
        // Crear un cliente Jersey
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URI);

        // Obtener todos los usuarios
        getAllUsuario(target);

        // Obtener un usuario por UUID
        UUID userId = UUID.fromString("385c5ae5-4141-4e92-9be4-c6b7a7c73ccb"); // Reemplaza con el ID del usuario que deseas buscar
        getUsuarioById(target, userId);

        // Cerrar el cliente
        client.close();
    }

    // Método para obtener todos los usuarios
    private static void getAllUsuario(WebTarget target) {
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        // Procesar la respuesta
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            List<Usuario> usuarios = response.readEntity(new GenericType<>() {});
            usuarios.forEach(usuario -> System.out.println(usuario.toString()));
        } else {
            System.out.println("Error: " + response.getStatus());
        }

        // Cerrar la respuesta
        response.close();
    }

    // Método para obtener un usuario por su UUID
    private static void getUsuarioById(WebTarget target, UUID userId) {
        // Agregar el UUID al URL de la solicitud
        WebTarget usuarioTarget = target.path(userId.toString()); // Usar userId.toString()

        // Realizar la solicitud GET para obtener el usuario
        Response response = usuarioTarget.request(MediaType.APPLICATION_JSON).get();

        // Procesar la respuesta
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            Usuario usuario = response.readEntity(Usuario.class);
            System.out.println("Usuario encontrado: " + usuario.toString());
        } else {
            System.out.println("Error al obtener el usuario por ID: " + response.getStatus());
        }

        // Cerrar la respuesta
        response.close();
    }
}
