package com.raul.henares.gestor_incidencias.Dtos;

import com.raul.henares.gestor_incidencias.Entidades.Rol;
import lombok.Data;

@Data
public class  ModificarUsuarioDto<T>  {
    private Long idUsuario;
    private String cambio;
    private T valor;
}
