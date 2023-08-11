package com.spectrum.ecommerce.repository;

import com.spectrum.ecommerce.model.Producto;
import com.spectrum.ecommerce.model.Usuario;
import com.spectrum.ecommerce.utilities.BaseDatos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductoRepositoryImpl implements ProductoRepository{




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

        List<Producto> listaProductos;

        try (EntityManager entity = BaseDatos.getEntityManagerFactory().createEntityManager()) {
            Query query = entity.createQuery("SELECT u FROM Producto u");
            listaProductos = query.getResultList();
        } catch (Exception e) {
            listaProductos = new ArrayList<>();
            e.printStackTrace();
        }
        return listaProductos;

    }

    @Override
    public Usuario getUsuarioById(UUID id) {
        return null;
    }

    @Override
    public List<Usuario> getProductoByCriterio(String criterio, Object valor) {
        return null;
    }
}
