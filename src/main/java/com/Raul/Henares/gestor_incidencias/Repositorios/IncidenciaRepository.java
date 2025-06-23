package com.Raul.Henares.gestor_incidencias.Repositorios;

import com.Raul.Henares.gestor_incidencias.Entidades.Estado;
import com.Raul.Henares.gestor_incidencias.Entidades.Incidencia;
import com.Raul.Henares.gestor_incidencias.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidenciaRepository extends JpaRepository<Incidencia,Long> {

    List<Incidencia> findByCliente(Usuario usuario);

    List<Incidencia> findByTecnico(Usuario usuario);

    List<Incidencia> findByEstado(Estado estado);
}
