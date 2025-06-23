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
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String titulo;

    @NonNull
    private String descripcion;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Prioridad prioridad;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @NonNull
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    //se debe añadir en el servicio
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;


    public Incidencia(Usuario tecnico, @NonNull Usuario cliente, LocalDateTime fechaCreacion, @NonNull Prioridad prioridad, @NonNull Estado estado, @NonNull String descripcion, @NonNull String titulo) {
        this.tecnico = tecnico;
        this.cliente = cliente;
        this.fechaCreacion = fechaCreacion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.descripcion = descripcion;
        this.titulo = titulo;
    }
}
