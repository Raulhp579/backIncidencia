package com.raul.henares.gestor_incidencias.Repositorios;

import com.raul.henares.gestor_incidencias.Entidades.Estado;
import com.raul.henares.gestor_incidencias.Entidades.Incidencia;
import com.raul.henares.gestor_incidencias.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidenciaRepository extends JpaRepository<Incidencia,Long> {

    List<Incidencia> findByCliente(Usuario usuario);

    List<Incidencia> findByTecnico(Usuario usuario);

    List<Incidencia> findByEstado(Estado estado);
}
