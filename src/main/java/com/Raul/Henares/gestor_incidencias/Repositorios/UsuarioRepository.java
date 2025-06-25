package com.raul.henares.gestor_incidencias.Repositorios;

import com.raul.henares.gestor_incidencias.Entidades.Rol;
import com.raul.henares.gestor_incidencias.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByNombre(String nombre);
    List<Usuario> findByRol(Rol rol);
}
