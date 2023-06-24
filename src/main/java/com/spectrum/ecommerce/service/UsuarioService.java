package com.spectrum.ecommerce.service;

import com.spectrum.ecommerce.model.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {

    boolean insertUsuario(Usuario usuario);
    boolean updateUsuario(Usuario usuario);
    boolean deleteUsuario(UUID id);
    List<Usuario> getAllUsuario();
    Usuario getUsuarioById(UUID id);
    List<Usuario> getUsuarioByCriterio(String criterio, Object valor);

    boolean validarCorreo(String correo);

}
