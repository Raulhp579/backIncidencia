package com.Raul.Henares.gestor_incidencias.Servicios;

import com.Raul.Henares.gestor_incidencias.Entidades.Rol;
import com.Raul.Henares.gestor_incidencias.Entidades.Usuario;
import com.Raul.Henares.gestor_incidencias.Repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll(){
        return this.usuarioRepository.findAll();
    }

    public Usuario getById(Long id){
        return this.usuarioRepository.getReferenceById(id);
    }

    public void crear(Usuario usuario){
        this.usuarioRepository.save(usuario);
    }

    public <T> void modificarUsuario(Long idUs, String cambio, T valor){
        Usuario usuario = this.usuarioRepository.getReferenceById(idUs);
        if (cambio.equalsIgnoreCase("nombre")){
            usuario.setNombre((String) valor);
        } else if (cambio.equalsIgnoreCase("email")) {
            usuario.setEmail((String) valor);
        } else if (cambio.equalsIgnoreCase("contraseña")) {
            usuario.setContraseña((String) valor);
        } else if (cambio.equalsIgnoreCase("rol")) {
            usuario.setRol((Rol) valor);
        }

        this.usuarioRepository.save(usuario);
    }

    public Usuario getPorNombre(String nombre){
        return this.usuarioRepository.findByNombre(nombre);
    }
}
