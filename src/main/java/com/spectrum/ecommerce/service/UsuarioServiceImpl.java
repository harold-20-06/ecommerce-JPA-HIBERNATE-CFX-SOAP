package com.spectrum.ecommerce.service;

import com.spectrum.ecommerce.model.Usuario;
import com.spectrum.ecommerce.repository.UsuarioRepository;
import com.spectrum.ecommerce.repository.UsuarioRepositoryImpl;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.UUID;

public class UsuarioServiceImpl implements UsuarioService{
    private static final UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
    }

    public UsuarioServiceImpl() {

    }


    @Override
    public boolean insertUsuario(Usuario usuario) {
        boolean esExitoso = usuarioRepository.insertUsuario(usuario);
        return esExitoso;
    }

    public boolean updateUsuario(Usuario usuario) {
        boolean esExitoso = usuarioRepository.updateUsuario(usuario);
        return esExitoso;
    }

    @Override
    public boolean deleteUsuario(UUID id) {
        boolean esExitoso = usuarioRepository.deleteUsuario(id);
        return esExitoso;
    }

    @Override
    public List<Usuario> getAllUsuario() {

        List<Usuario> usuarios = usuarioRepository.getAllUsuario();
        System.out.println("service "+usuarios.size());
        return usuarios;
    }


    public Usuario getUsuarioById(UUID id) {
        Usuario usuario = usuarioRepository.getUsuarioById(id);
        return usuario;
    }

    public List<Usuario> getUsuarioByCriterio(String criterio, Object valor) {
        List<Usuario> usuarios = usuarioRepository.getUsuarioByCriterio(criterio,valor);
        return usuarios;
    }

    public boolean validarCorreo(String correo) {
        // Expresión regular para verificar el formato del correo electrónico
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";


        if (correo.matches(regex)) {
            return true; // El correo es válido
        } else {
            return false; // El correo es inválido
        }
    }


}
