package com.spectrum.ecommerce.controller;


import com.spectrum.ecommerce.model.Producto;
import com.spectrum.ecommerce.model.Usuario;
import com.spectrum.ecommerce.service.ProductoService;
import com.spectrum.ecommerce.service.ProductoServiceImpl;
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


    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
    public Response updateUsuario(@PathParam("id") UUID id, Producto producto) {
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
