package com.Raul.Henares.gestor_incidencias.Controladores;

import com.Raul.Henares.gestor_incidencias.Entidades.Estado;
import com.Raul.Henares.gestor_incidencias.Entidades.Incidencia;
import com.Raul.Henares.gestor_incidencias.Servicios.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidence")
@CrossOrigin(origins = "http://localhost:4200")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping("/getByClient/{id}")
    public List<Incidencia> getPorCliente(@PathVariable("id")Long id) throws Exception{
        return this.incidenciaService.getPorCliente(id);
    }

    @GetMapping("/getByTechnical/{id}")
    public List<Incidencia> getPorTecnico(@PathVariable("id") Long id) throws Exception{
        return this.incidenciaService.getPorTecnico(id);
    }

    @PostMapping("/addIncidence")
    public void crearIncidencia(@RequestBody Incidencia incidencia){
        this.incidenciaService.crear(incidencia);
    }

    @PutMapping("/changeStatus/{id}/{estado}")
    public void cambiarEstado(@PathVariable("id")Long idInc, @PathVariable("estado")Estado estado){
        this.incidenciaService.actualizarEstado(idInc,estado);
    }

    @PutMapping("addTechnical/{idInc}/{idUser}")
    public void asignarTecnico(@PathVariable("idInc")Long idInc,@PathVariable("idUser") Long idUser){
        this.incidenciaService.asignarTecnico(idInc,idUser);
    }

    @GetMapping("getIncidence/{id}")
    public Incidencia getIncidencia(@PathVariable("id")Long id){
        return this.incidenciaService.getById(id);
    }

    @GetMapping("/showAll")
    public List<Incidencia> getIncidencias(){
        return this.incidenciaService.obtenerIncidencias();
    }

    @GetMapping("/findByNombre/{nombre}")
    public List<Incidencia> getByNombre(@PathVariable("nombre") String nombre) throws Exception {
        return  this.incidenciaService.getPorNombre(nombre);
    }
}
