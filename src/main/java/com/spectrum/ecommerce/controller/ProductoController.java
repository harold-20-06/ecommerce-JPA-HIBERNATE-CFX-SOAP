package com.spectrum.ecommerce.controller;


import com.spectrum.ecommerce.model.Producto;
import com.spectrum.ecommerce.model.Usuario;
import com.spectrum.ecommerce.service.ProductoService;
import com.spectrum.ecommerce.service.ProductoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/productos")
public class ProductoController {
    @Inject
    private ProductoService productoService;

    @Tag(name = "Productos", description = "API para operaciones relacionadas con los productos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente")
    @ApiResponse(responseCode = "500", description = "Error al obtener la lista de productos")
    public Response getAllProductos() {
        try {
            List<Producto> productos = productoService.getAllProducto();
            System.out.println("Productos en el controlador: " + productos.size());
            return Response.ok(productos).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener la lista de productos")
                    .build();
        }
    }

     @GET
     @Path("/{id}")
     @Produces(MediaType.APPLICATION_JSON)
        @Operation(
                summary = "Obtener un producto por ID",
                description = "Devuelve el producto correspondiente al ID proporcionado",
                responses = {
                        @ApiResponse(
                                responseCode = "200",
                                description = "Producto encontrado correctamente"
                        ),
                        @ApiResponse(
                                responseCode = "404",
                                description = "Producto no encontrado"
                        ),
                        @ApiResponse(
                                responseCode = "500",
                                description = "Error al obtener el producto"
                        )
                }
        )
     public Response getProductoById(@PathParam("id") UUID id) {
        try {
            Producto producto = productoService.getProductoById(id);
            if (producto == null) {
                System.out.println("Producto con ID " + id + " no encontrado.");
                return Response.status(Response.Status.NOT_FOUND).entity("Producto no encontrado").build();
            }
            return Response.ok(producto).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener el producto")
                    .build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Crear un producto", description = "Crea un nuevo producto")
    @ApiResponse(responseCode = "200", description = "Producto creado correctamente")
    @ApiResponse(responseCode = "500", description = "Error al crear el producto")

    public Response createProducto(Producto producto) {
        System.out.println("Creando producto: " + producto);
        try {
            productoService.insertProducto(producto);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el producto")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar un producto", description = "Actualiza un producto existente")
    @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @ApiResponse(responseCode = "500", description = "Error al actualizar el producto")

    public Response updateProducto(@PathParam("id") UUID id, Producto producto) {
        try {
            Producto existingProducto = productoService.getProductoById(id);
            if (existingProducto == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"mensaje\": \"Usuario no encontrado\"}")
                        .header("Content-Type", "application/json")
                        .build();
            }
            producto.setId(id);
            productoService.updateProducto(producto);
            return Response.ok(producto).build();
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
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto existente")
    @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente")
    @ApiResponse(responseCode = "500", description = "Error al eliminar el producto")

    public Response deleteProducto(@PathParam("id") UUID id) {
        try {
            productoService.deleteProducto(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al eliminar el producto")
                    .build();
        }
    }
}
