package com.spectrum.ecommerce.controller;

import com.spectrum.ecommerce.model.Usuario;
import com.spectrum.ecommerce.service.UsuarioService;
import com.spectrum.ecommerce.service.UsuarioServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/usuarios")
public class UsuarioController {
    @Inject
    private UsuarioService usuarioService;

    @GET
    @Produces(MediaType.APPLICATION_JSON) // Define que este método responde con JSON
    public Response getAllUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.getAllUsuario();
            System.out.println("Usuarios en el controlador: " + usuarios.size());
            return Response.ok(usuarios).build();
        } catch (Exception e) {
            e.printStackTrace(); // Para depuración
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener la lista de usuarios")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioById(@PathParam("id") UUID id) { // UUID en lugar de int
        try {
            Usuario usuario = usuarioService.getUsuarioById(id); // Llamar al servicio
            if (usuario == null) {
                System.out.println("Usuario con ID " + id + " no encontrado.");
                return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
            }
            return Response.ok(usuario).build();
        } catch (Exception e) {
            e.printStackTrace(); // Para depuración
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener el usuario")
                    .build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response createUsuario(Usuario usuario) {
        System.out.println("Creando usuario: " + usuario);
        try {
            // Lógica para insertar el usuario en la base de datos
            usuarioService.insertUsuario(usuario);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            // Manejar la excepción y devolver una respuesta apropiada
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al insertar el usuario")
                    .build();
        }
    }

@PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@PathParam("id") UUID id, Usuario usuario) {
        try {
            Usuario existingUsuario = usuarioService.getUsuarioById(id);
            if (existingUsuario == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"mensaje\": \"Usuario no encontrado\"}")
                        .header("Content-Type", "application/json")
                        .build();
            }
            usuario.setId(id);
            usuarioService.updateUsuario(usuario);
            return Response.ok(usuario).build();
        } catch (Exception e) {
            e.printStackTrace(); // Para depuración
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al actualizar el usuario")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUsuario(@PathParam("id") UUID id) {
        try {
            Usuario usuario = usuarioService.getUsuarioById(id);
            if (usuario == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
            }
            usuarioService.deleteUsuario(id);
            return Response.noContent().build();
        } catch (Exception e) {
            e.printStackTrace(); // Para depuración
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al eliminar el usuario")
                    .build();
        }
    }

    

}

