package com.Raul.Henares.gestor_incidencias.Servicios;

import com.Raul.Henares.gestor_incidencias.Entidades.Estado;
import com.Raul.Henares.gestor_incidencias.Entidades.Incidencia;
import com.Raul.Henares.gestor_incidencias.Entidades.Rol;
import com.Raul.Henares.gestor_incidencias.Entidades.Usuario;
import com.Raul.Henares.gestor_incidencias.Repositorios.IncidenciaRepository;
import com.Raul.Henares.gestor_incidencias.Repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository incidenciaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void crear(Incidencia incidencia){
        this.incidenciaRepository.save(incidencia);
    }

    public Incidencia getById(Long id){
        return this.incidenciaRepository.getReferenceById(id);
    }

    public List<Incidencia> getPorCliente(Long clienteId) throws Exception {
        Usuario usuario = this.usuarioRepository.getReferenceById(clienteId);
        if (usuario.getRol().equals(Rol.CLIENTE)){
            return this.incidenciaRepository.findByCliente(usuario);
        }else {
            throw new Exception("Debe ser un cliente");
        }
    }

    public List<Incidencia> getPorNombre(String nombre) throws Exception {
        Usuario usuario = this.usuarioRepository.findByNombre(nombre);
        return this.incidenciaRepository.findByCliente(usuario);
    }

    public List<Incidencia> getPorTecnico(Long tecnicoId) throws Exception {
        Usuario usuario = this.usuarioRepository.getReferenceById(tecnicoId);
        if (usuario.getRol().equals(Rol.TECNICO)){
            return this.incidenciaRepository.findByTecnico(usuario);
        }else {
            throw new Exception("Debe ser un Tecnico");
        }
    }

    public void actualizarEstado(Long idInc, Estado estado){//revisar si se admite asi en el json
        Incidencia incidencia = this.incidenciaRepository.getReferenceById(idInc);
        incidencia.setEstado(estado);
        this.incidenciaRepository.save(incidencia);
    }

    public void asignarTecnico(Long idInc, Long idUsu){
        if (this.usuarioRepository.getReferenceById(idUsu).getRol().equals(Rol.TECNICO)) {
            Incidencia incidencia = this.incidenciaRepository.getReferenceById(idInc);
            Usuario usuario = this.usuarioRepository.getReferenceById(idUsu);
            incidencia.setTecnico(usuario);
            this.incidenciaRepository.save(incidencia);
        }
    }

    public List<Incidencia> obtenerIncidencias(){
        return this.incidenciaRepository.findAll();
    }
}
