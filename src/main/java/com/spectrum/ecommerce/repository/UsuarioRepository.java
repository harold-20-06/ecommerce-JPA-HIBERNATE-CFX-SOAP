package com.spectrum.ecommerce.repository;

import com.spectrum.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsuarioRepository  {


    boolean insertUsuario(Usuario usuario);
    boolean updateUsuario(Usuario usuario);

    boolean deleteUsuario(UUID id);

    List<Usuario> getAllUsuario();

    Usuario getUsuarioById(UUID id);

    List<Usuario> getUsuarioByCriterio(String criterio, Object valor);



}

