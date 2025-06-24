package com.raul.henares.gestor_incidencias.Servicios;

import com.raul.henares.gestor_incidencias.Dtos.IncidenciaDto;
import com.raul.henares.gestor_incidencias.Entidades.*;
import com.raul.henares.gestor_incidencias.Repositorios.IncidenciaRepository;
import com.raul.henares.gestor_incidencias.Repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository incidenciaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Incidencia crear(IncidenciaDto dto){
        Usuario cliente = this.usuarioRepository.getReferenceById(dto.getClienteId());
        String titulo = dto.getTitulo();
        String descripcion = dto.getDescripcion();
        Estado estado = dto.getEstado();
        Prioridad prioridad = dto.getPrioridad();
        LocalDateTime fecha = dto.getFecha();

        Incidencia incidencia;
        if (dto.getTecnicoId()!=null) {
            Usuario tecnico = this.usuarioRepository.getReferenceById(dto.getTecnicoId());
            incidencia = new Incidencia(tecnico,cliente,prioridad,estado,descripcion,titulo);
        }else {
            incidencia = new Incidencia(titulo,descripcion,estado,prioridad,cliente);
        }
         return this.incidenciaRepository.save(incidencia);
    }

    public IncidenciaDto getById(Long id){
        Incidencia inc = this.incidenciaRepository.getReferenceById(id);
        return getIncidenciaDto(inc);
    }

    public List<IncidenciaDto> getPorId(Long clienteId) throws Exception {
        Usuario usuario = this.usuarioRepository.getReferenceById(clienteId);

            List<Incidencia> incidencias = this.incidenciaRepository.findByCliente(usuario);
            List<IncidenciaDto> incidenciaDtos = new ArrayList<>();
            for (Incidencia inc : incidencias){
                IncidenciaDto dto = getIncidenciaDto(inc);
                incidenciaDtos.add(dto);
            }
            return incidenciaDtos;

    }

    private static IncidenciaDto getIncidenciaDto(Incidencia inc) {
        IncidenciaDto dto = new IncidenciaDto();
        dto.setId(inc.getId());
        dto.setTitulo(inc.getTitulo());
        dto.setDescripcion(inc.getDescripcion());
        dto.setEstado(inc.getEstado());
        dto.setPrioridad(inc.getPrioridad());
        dto.setFecha(inc.getFechaCreacion());
        dto.setClienteId(inc.getCliente().getId());
        if (inc.getTecnico()!=null){
            dto.setTecnicoId(inc.getTecnico().getId());
        }
        return dto;
    }

    public List<IncidenciaDto> getPorNombre(String nombre) throws Exception {
        Usuario usuario = this.usuarioRepository.findByNombre(nombre);
        List<Incidencia> incidencias = this.incidenciaRepository.findByCliente(usuario);
        List<IncidenciaDto> incidenciaDtos = new ArrayList<>();
        for (Incidencia inc : incidencias){
            IncidenciaDto dto = getIncidenciaDto(inc);
            incidenciaDtos.add(dto);
        }
        return incidenciaDtos;
    }

    public List<IncidenciaDto> getPorTecnico(Long tecnicoId) throws Exception {
        Usuario usuario = this.usuarioRepository.getReferenceById(tecnicoId);
        List<Incidencia> incidencias= this.incidenciaRepository.findByTecnico(usuario);
        List<IncidenciaDto> incidenciaDtos= new ArrayList<>();
        if (usuario.getRol().equals(Rol.TECNICO)){
            for (Incidencia inc : incidencias) {
                IncidenciaDto dto = getIncidenciaDto(inc);
                incidenciaDtos.add(dto);
            }
            return incidenciaDtos;
        }else {
            throw new Exception("Debe ser un Tecnico");
        }
    }

    public Incidencia actualizarEstado(Long idInc, Estado estado){
        Incidencia incidencia = this.incidenciaRepository.getReferenceById(idInc);
        incidencia.setEstado(estado);
        return this.incidenciaRepository.save(incidencia);
    }

    public void asignarTecnico(Long idInc, Long idUsu){
        if (this.usuarioRepository.getReferenceById(idUsu).getRol().equals(Rol.TECNICO)) {
            Incidencia incidencia = this.incidenciaRepository.getReferenceById(idInc);
            Usuario usuario = this.usuarioRepository.getReferenceById(idUsu);
            incidencia.setTecnico(usuario);
            this.incidenciaRepository.save(incidencia);
        }
    }

    public List<IncidenciaDto> obtenerIncidencias(){
        List<Incidencia> incidencias = this.incidenciaRepository.findAll();
        List<IncidenciaDto> incidenciaDtos = new ArrayList<>();

        for (Incidencia inc : incidencias){
            IncidenciaDto dto = getIncidenciaDto(inc);
            incidenciaDtos.add(dto);
        }
        return incidenciaDtos;
    }
}
