package com.raul.henares.gestor_incidencias.Servicios;

import com.raul.henares.gestor_incidencias.Dtos.ComentarioDto;
import com.raul.henares.gestor_incidencias.Entidades.Comentario;
import com.raul.henares.gestor_incidencias.Repositorios.ComentarioRepository;
import com.raul.henares.gestor_incidencias.Repositorios.IncidenciaRepository;
import com.raul.henares.gestor_incidencias.Repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private IncidenciaRepository incidenciaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public Comentario crear(ComentarioDto dto){
        Comentario comentario = new Comentario();
        comentario.setMensaje(dto.getMensaje());
        comentario.setAutor(this.usuarioRepository.getReferenceById(dto.getAutorId()));
        comentario.setIncidencia(this.incidenciaRepository.getReferenceById(dto.getIncidenciaId()));
        return this.comentarioRepository.save(comentario);
    }

    public static ComentarioDto getComentarioDto(Comentario comentario){
        ComentarioDto dto = new ComentarioDto();
        dto.setId(comentario.getId());
        dto.setMensaje(comentario.getMensaje());
        dto.setFecha(comentario.getFecha());
        dto.setAutorId(comentario.getAutor().getId());
        dto.setIncidenciaId(comentario.getIncidencia().getId());
        return dto;
    }
    public List<ComentarioDto> getPorIncidencia(Long idInc){
        List<Comentario> comentarios = this.comentarioRepository.findByIncidencia(this.incidenciaRepository.getReferenceById(idInc));
        List<ComentarioDto> dtos = new ArrayList<>();

        for (Comentario coment : comentarios){
            ComentarioDto dto = getComentarioDto(coment);
            dtos.add(dto);
        }
        return dtos;
    }


}
