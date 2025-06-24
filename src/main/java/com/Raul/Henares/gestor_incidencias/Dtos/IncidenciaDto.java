package com.raul.henares.gestor_incidencias.Dtos;

import com.raul.henares.gestor_incidencias.Entidades.Estado;
import com.raul.henares.gestor_incidencias.Entidades.Prioridad;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidenciaDto {
    private Long id;
    private String titulo;
    private String descripcion;
    private Estado estado;
    private Prioridad prioridad;
    private LocalDateTime fecha;
    private Long clienteId;
    private Long tecnicoId;
}
