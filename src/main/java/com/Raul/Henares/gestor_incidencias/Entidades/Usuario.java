package com.Raul.Henares.gestor_incidencias.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})//Esto permite que Jackson ignore internamente los campos proxy que
                                                            // genera Hibernate cuando el objeto Usuario viene
                                                            //cargado como parte de otra entidad.
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String nombre;

    @NonNull
    private String email;

    @NonNull
    private String contraseña;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
