package com.raul.henares.gestor_incidencias.Controladores;

import com.raul.henares.gestor_incidencias.Dtos.IncidenciaDto;
import com.raul.henares.gestor_incidencias.Dtos.ModificarIncidenciaDto;
import com.raul.henares.gestor_incidencias.Entidades.Estado;
import com.raul.henares.gestor_incidencias.Entidades.Incidencia;
import com.raul.henares.gestor_incidencias.Servicios.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/incidence")
@CrossOrigin(origins = "http://localhost:4200")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<List<IncidenciaDto>> getPorId(@PathVariable("id")Long id) throws Exception{
        try {
            List<IncidenciaDto> respuesta = this.incidenciaService.getPorId(id);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/getByTechnical/{id}")
    public ResponseEntity<List<IncidenciaDto>> getPorTecnico(@PathVariable("id") Long id) throws Exception{
        try {
            List<IncidenciaDto> respuesta = this.incidenciaService.getPorTecnico(id);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }

    @PostMapping("/addIncidence")
    public ResponseEntity<Map<String,String>> crearIncidencia(@RequestBody IncidenciaDto dto){
        try {
             this.incidenciaService.crear(dto);
             Map<String ,String> resp = new HashMap<>();
             resp.put("respuesta","Incidencia creada correctamente");
             return ResponseEntity.ok(resp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
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
    public ResponseEntity<IncidenciaDto> getIncidencia(@PathVariable("id")Long id){
        try {
            IncidenciaDto respuesta = this.incidenciaService.getById(id);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }

    @GetMapping("/showAll")
    public ResponseEntity<List<IncidenciaDto>> getIncidencias(){
        try {
            List<IncidenciaDto> respuesta = this.incidenciaService.obtenerIncidencias();
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }

    @GetMapping("/findByNombre/{nombre}")
    public ResponseEntity<List<IncidenciaDto>> getByNombre(@PathVariable("nombre") String nombre) throws Exception {
        try {
            List<IncidenciaDto> respuesta = this.incidenciaService.getPorNombre(nombre);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/findByEstado/{estado}")
    public ResponseEntity<List<IncidenciaDto>> getPorEstado(@PathVariable("estado")Estado estado){
        try {
            List<IncidenciaDto> respuesta = this.incidenciaService.getPorEstado(estado);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/modifyIncidence")
    public ResponseEntity<Map<String,String>> modificarIncidencia(@RequestBody ModificarIncidenciaDto dto){
        try {
            this.incidenciaService.modificarIncidencia(dto);
            HashMap<String,String> respuesta = new HashMap<>();
            respuesta.put("respuesta","incidencia modificada correctamente");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
