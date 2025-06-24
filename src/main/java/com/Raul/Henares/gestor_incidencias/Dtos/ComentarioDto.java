package com.raul.henares.gestor_incidencias.Dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComentarioDto {
    private Long id;
    private String mensaje;
    private LocalDateTime fecha;
    private Long autorId;
    private Long incidenciaId;
}
