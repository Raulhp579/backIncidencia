package com.Raul.Henares.gestor_incidencias.Repositorios;

import com.Raul.Henares.gestor_incidencias.Entidades.Comentario;
import com.Raul.Henares.gestor_incidencias.Entidades.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByIncidencia(Incidencia incidencia);
}
