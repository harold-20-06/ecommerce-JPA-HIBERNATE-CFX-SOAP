package com.spectrum.ecommerce.service;

import com.spectrum.ecommerce.model.Producto;
import com.spectrum.ecommerce.model.Usuario;
import com.spectrum.ecommerce.repository.ProductoRepository;
import com.spectrum.ecommerce.repository.ProductoRepositoryImpl;
import com.spectrum.ecommerce.repository.UsuarioRepository;
import com.spectrum.ecommerce.repository.UsuarioRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProductoServiceImpl implements ProductoService {
    @Inject
    private ProductoRepository productoRepository;

    //public ProductoServiceImpl(ProductoRepositoryImpl productoRepository) {}


    @Override
    public boolean insertProducto(Producto producto) {

        boolean esExitoso = productoRepository.insertProducto(producto);
        return esExitoso;
    }

    @Override
    public boolean updateProducto(Producto producto) {

    boolean esExitoso = productoRepository.updateProducto(producto);
        return esExitoso;
    }
    @Override
    public boolean deleteProducto(UUID id) {
        boolean esExitoso = productoRepository.deleteProducto(id);
        return esExitoso;
    }

    @Override
    public List<Producto> getAllProducto() {
    List<Producto> productos = productoRepository.getAllProducto();
        return productos;
    }

    @Override
    public Producto getProductoById(UUID id) {
        Producto productoById = productoRepository.getProductoById(id);
        return productoById;
    }

    @Override
    public List<Producto> getProductoByCriterio(String criterio, Object valor) {
        List<Producto> productos = productoRepository.getProductoByCriterio(criterio,valor);

        return productos;
    }

    @Override
    public boolean validarCorreo(String correo) {
        return false;
    }
}
