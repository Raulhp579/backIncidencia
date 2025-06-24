package com.raul.henares.gestor_incidencias.Dtos;

import com.raul.henares.gestor_incidencias.Entidades.Rol;
import lombok.Data;

@Data
public class UsuarioDto {
    private Long id;
    private String nombre;
    private String email;
    private String contrasenya;
    private Rol rol;
}
