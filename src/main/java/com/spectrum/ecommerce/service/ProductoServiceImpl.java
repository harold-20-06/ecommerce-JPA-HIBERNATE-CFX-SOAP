package com.spectrum.ecommerce.service;

import com.spectrum.ecommerce.model.Producto;
import com.spectrum.ecommerce.model.Usuario;
import com.spectrum.ecommerce.repository.ProductoRepository;
import com.spectrum.ecommerce.repository.ProductoRepositoryImpl;
import com.spectrum.ecommerce.repository.UsuarioRepository;
import com.spectrum.ecommerce.repository.UsuarioRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class ProductoServiceImpl implements ProductoService {

    private static final ProductoRepository productoRepository = new ProductoRepositoryImpl();

    public ProductoServiceImpl(ProductoRepositoryImpl productoRepository) {
    }


    @Override
    public boolean insertProducto(Producto producto) {
        return false;
    }

    @Override
    public boolean updateProducto(Producto producto) {
        return false;
    }

    @Override
    public boolean deleteProducto(UUID id) {
        return false;
    }

    @Override
    public List<Producto> getAllProducto() {
    List<Producto> productos = productoRepository.getAllProducto();
        return productos;
    }

    @Override
    public Usuario getProductoById(UUID id) {
        return null;
    }

    @Override
    public List<Usuario> getProductoByCriterio(String criterio, Object valor) {
        return null;
    }

    @Override
    public boolean validarCorreo(String correo) {
        return false;
    }
}
