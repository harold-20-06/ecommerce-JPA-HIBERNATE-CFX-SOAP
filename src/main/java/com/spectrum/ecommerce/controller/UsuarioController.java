package com.spectrum.ecommerce.controller;

import com.spectrum.ecommerce.model.Usuario;
import com.spectrum.ecommerce.service.UsuarioService;
import com.spectrum.ecommerce.service.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Tag(name = "Usuarios", description = "API para operaciones relacionadas con los usuarios")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error al obtener la lista de usuarios")
    })
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
    @Operation(
            summary = "Obtener un usuario por ID",
            description = "Devuelve el usuario correspondiente al ID proporcionado",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuario encontrado correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Usuario.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuario no encontrado"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error al obtener el usuario"
                    )
            }
    )
    public Response getUsuarioById(@PathParam("id") UUID id) {
        try {
            Usuario usuario = usuarioService.getUsuarioById(id);
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
    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Crea un nuevo usuario con los datos proporcionados",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Usuario creado correctamente"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error al insertar el usuario"
                    )
            }
    )
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
    @Operation(
            summary = "Actualizar un usuario",
            description = "Actualiza los datos de un usuario con el ID proporcionado",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuario actualizado correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Usuario.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuario no encontrado"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error al actualizar el usuario"
                    )
            }
    )
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
    @Operation(
            summary = "Eliminar un usuario",
            description = "Elimina un usuario con el ID proporcionado",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Usuario eliminado correctamente"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuario no encontrado"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error al eliminar el usuario"
                    )
            }
    )
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

