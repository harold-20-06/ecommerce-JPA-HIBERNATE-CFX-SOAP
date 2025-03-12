package com.spectrum.ecommerce.controller;


import com.spectrum.ecommerce.model.Producto;
import com.spectrum.ecommerce.service.ProductoService;
import com.spectrum.ecommerce.service.ProductoServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/productos")
public class ProductoController {
    @Inject
    private ProductoService productoService;


    @GET
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


}
