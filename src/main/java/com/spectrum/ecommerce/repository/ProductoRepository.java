package com.spectrum.ecommerce.repository;

import com.spectrum.ecommerce.model.Producto;
import com.spectrum.ecommerce.model.Usuario;

import java.util.List;
import java.util.UUID;

public interface ProductoRepository {


    boolean insertProducto(Producto producto);
    boolean updateProducto(Producto producto);

    boolean deleteProducto(UUID id);

    List<Producto> getAllProducto();

    Producto getProductoById(UUID id);

    List<Producto> getProductoByCriterio(String criterio, Object valor);


}
