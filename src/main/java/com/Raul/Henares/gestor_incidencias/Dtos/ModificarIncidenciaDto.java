package com.raul.henares.gestor_incidencias.Dtos;

import lombok.Data;

@Data
public class ModificarIncidenciaDto<T> {
    private Long id;
    private String cambio;
    private T valor;
}
