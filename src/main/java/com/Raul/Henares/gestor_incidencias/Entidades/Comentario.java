package com.Raul.Henares.gestor_incidencias.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String mensaje;

    private LocalDateTime fecha = LocalDateTime.now();

    @NonNull
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "incidencia_id")
    private Incidencia incidencia;
}
