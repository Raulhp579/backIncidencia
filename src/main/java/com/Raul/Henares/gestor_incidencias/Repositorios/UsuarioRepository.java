package com.Raul.Henares.gestor_incidencias.Repositorios;

import com.Raul.Henares.gestor_incidencias.Entidades.Incidencia;
import com.Raul.Henares.gestor_incidencias.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByNombre(String nombre);
}
