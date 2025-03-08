package com.spectrum.ecommerce.controller;

import com.spectrum.ecommerce.model.Usuario;
import com.spectrum.ecommerce.service.UsuarioService;
import com.spectrum.ecommerce.service.UsuarioServiceImpl;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioServiceImpl();

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
}

/*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUsuario(Usuario usuario) {
        usuario.setId(usuarios.size() + 1);
        usuarios.add(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@PathParam("id") int id, Usuario usuario) {
        Usuario existingUsuario = usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
        if (existingUsuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingUsuario.setNombre(usuario.getNombre());
        existingUsuario.setEmail(usuario.getEmail());
        return Response.ok(existingUsuario).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUsuario(@PathParam("id") int id) {
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        usuarios.remove(usuario);
        return Response.noContent().build();
    }*/

/*
public class UsuarioController {

    private static List<Usuario> usuarios = new ArrayList<>();
    private final UsuarioServiceImpl usuarioService = new UsuarioServiceImpl();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsuarios() {
        try {
            usuarios = usuarioService.getAllUsuario();
            return Response.ok(usuarios).build();
        } catch (Exception e) {
            // Manejar la excepción y devolver una respuesta apropiada
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener la lista de usuarios")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioById(@PathParam("id") int id) {
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(usuario).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUsuario(Usuario usuario) {
        usuario.setId(usuarios.size() + 1);
        usuarios.add(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@PathParam("id") int id, Usuario usuario) {
        Usuario existingUsuario = usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
        if (existingUsuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingUsuario.setNombre(usuario.getNombre());
        existingUsuario.setEmail(usuario.getEmail());
        return Response.ok(existingUsuario).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUsuario(@PathParam("id") int id) {
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        usuarios.remove(usuario);
        return Response.noContent().build();
    }
}*/
