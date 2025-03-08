package com.spectrum.ecommerce.repository;

import com.spectrum.ecommerce.model.Pedido;
import com.spectrum.ecommerce.model.Usuario;
import com.spectrum.ecommerce.utilities.BaseDatos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Override
    public boolean insertUsuario(Usuario usuario) {
        boolean esExitoso = false;

        try (EntityManager entity = BaseDatos.getEntityManagerFactory().createEntityManager()) {
            entity.getTransaction().begin();
            entity.persist(usuario);
            entity.getTransaction().commit();
            esExitoso = true;
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return esExitoso;
    }
    @Override
    public boolean updateUsuario(Usuario usuario) {
        boolean esExitoso = false;

        try (EntityManager entity = BaseDatos.getEntityManagerFactory().createEntityManager()) {
            entity.getTransaction().begin();
            entity.merge(usuario);
            entity.getTransaction().commit();
            esExitoso = true;
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return esExitoso;
    }

    @Override
    public boolean deleteUsuario(UUID id) {
        boolean esExitoso = false;

        try (EntityManager entity = BaseDatos.getEntityManagerFactory().createEntityManager()) {
            entity.getTransaction().begin();

            // Obtener el usuario por su ID
            Usuario usuario = getUsuarioById(id);

            if (usuario != null) {
                // Eliminar el usuario
                entity.remove(entity.merge(usuario));
                entity.getTransaction().commit();
                esExitoso = true;
            } else {
                System.out.println("No se encontró ningún usuario con el ID proporcionado.");
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return esExitoso;
    }

    @Override
    public List<Usuario> getAllUsuario() {
        List<Usuario> listaUsuarios;

          try (EntityManager entity = BaseDatos.getEntityManagerFactory().createEntityManager()) {
            Query query = entity.createQuery("SELECT u FROM Usuario u");
            listaUsuarios = query.getResultList();
            System.out.println("Usuarios en el repositorio: " + listaUsuarios.size());
            //for (Usuario usuario : listaUsuarios) {
            //    usuario.getPedidos().size(); // Carga la colección de pedidos
            //}

        } catch (Exception e) {
            System.out.println("Error en repository");
            listaUsuarios = new ArrayList<>();
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    @Override
    public Usuario getUsuarioById(UUID id) {
        try (EntityManager entity = BaseDatos.getEntityManagerFactory().createEntityManager()) {
            Usuario usuario = entity.find(Usuario.class, id);
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Usuario> getUsuarioByCriterio(String criterio, Object valor) {
        String jpqlQuery = "SELECT u FROM Usuario u WHERE ";

        try (EntityManager entity = BaseDatos.getEntityManagerFactory().createEntityManager()) {
            // Construir la consulta JPQL según el criterio y el valor proporcionados
            if (criterio.equals("id")) {
                jpqlQuery += "u.id = :valor";
            } else if (criterio.equals("nombre")) {
                jpqlQuery += "u.nombre = :valor";
            } else if (criterio.equals("apellido1")) {
                jpqlQuery += "u.apellido1 = :valor";
            } else if (criterio.equals("apellido2")) {
                jpqlQuery += "u.apellido2 = :valor";
            } else if (criterio.equals("email")) {
                jpqlQuery += "u.email = :valor";
            } else {
                throw new IllegalArgumentException("Criterio de búsqueda inválido: " + criterio);
            }

            TypedQuery<Usuario> query = entity.createQuery(jpqlQuery, Usuario.class);
            query.setParameter("valor", valor);

            return query.getResultList();
        } catch (Exception e) {
            // Manejar la excepción según tus necesidades (lanzar otra excepción, mostrar mensaje, etc.)
            throw new RuntimeException("Error al buscar usuarios por criterio: " + e.getMessage(), e);
        }
    }

}
