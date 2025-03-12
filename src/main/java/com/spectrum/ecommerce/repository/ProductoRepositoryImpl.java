package com.spectrum.ecommerce.repository;

import com.spectrum.ecommerce.model.Producto;
import com.spectrum.ecommerce.model.Usuario;
import com.spectrum.ecommerce.utilities.BaseDatos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProductoRepositoryImpl implements ProductoRepository{

    @Override
    public boolean insertProducto(Producto producto) {
        Boolean esExitoso = false;
        try (EntityManager entity = BaseDatos.getEntityManagerFactory().createEntityManager()) {
            entity.getTransaction().begin();
            entity.persist(producto);
            entity.getTransaction().commit();
            esExitoso = true;

            return esExitoso;
        }
    }
    @Override
    public boolean updateProducto(Producto producto) {
        Boolean esExitoso = false;
        try (EntityManager entity = BaseDatos.getEntityManagerFactory().createEntityManager()) {
            entity.getTransaction().begin();
            entity.merge(producto);
            entity.getTransaction().commit();
            esExitoso = true;

            return esExitoso;
        }
    }
    @Override
    public boolean deleteProducto(UUID id) {
        boolean esExitoso = false;
        try (EntityManager entity = BaseDatos.getEntityManagerFactory().createEntityManager()) {
            entity.getTransaction().begin();
            Producto producto = entity.find(Producto.class, id);
            if (producto != null) {
                entity.remove(producto);
                entity.getTransaction().commit();
                esExitoso = true;
            }
            return esExitoso;
        }
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
    public Producto getProductoById(UUID id) {
        try (EntityManager entity = BaseDatos.getEntityManagerFactory().createEntityManager()) {
            return entity.find(Producto.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Producto> getProductoByCriterio(String criterio, Object valor) {
        try (EntityManager entity = BaseDatos.getEntityManagerFactory().createEntityManager()) {
            Query query = entity.createQuery("SELECT u FROM Producto u WHERE u." + criterio + " = :valor");
            query.setParameter("valor", valor);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
