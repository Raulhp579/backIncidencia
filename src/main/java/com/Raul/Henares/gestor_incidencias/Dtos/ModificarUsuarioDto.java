package com.raul.henares.gestor_incidencias.Dtos;

import com.raul.henares.gestor_incidencias.Entidades.Rol;
import lombok.Data;

@Data
public class  ModificarUsuarioDto  {
    private Long idUsuario;
    private String cambio;
    private String valor;
}
