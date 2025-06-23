package com.raul.henares.gestor_incidencias.Servicios;

import com.raul.henares.gestor_incidencias.Entidades.Comentario;
import com.raul.henares.gestor_incidencias.Repositorios.ComentarioRepository;
import com.raul.henares.gestor_incidencias.Repositorios.IncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private IncidenciaRepository incidenciaRepository;

    public void crear(Comentario comentario){
        this.comentarioRepository.save(comentario);
    }

    public List<Comentario> getPorIncidencia(Long idInc){
        return this.comentarioRepository.findByIncidencia(
                this.incidenciaRepository.getReferenceById(idInc)
        );
    }


}
