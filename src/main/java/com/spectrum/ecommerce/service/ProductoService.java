package com.spectrum.ecommerce.service;

import com.spectrum.ecommerce.model.Producto;
import com.spectrum.ecommerce.model.Usuario;

import java.util.List;
import java.util.UUID;

public interface ProductoService {

    boolean insertProducto(Producto producto);
    boolean updateProducto(Producto producto);
    boolean deleteProducto(UUID id);
    List<Producto> getAllProducto();
    Producto getProductoById(UUID id);
    List<Producto> getProductoByCriterio(String criterio, Object valor);

    boolean validarCorreo(String correo);



}
