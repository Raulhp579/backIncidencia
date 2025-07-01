package com.raul.henares.gestor_incidencias.Controladores;

import com.raul.henares.gestor_incidencias.Dtos.ComentarioDto;
import com.raul.henares.gestor_incidencias.Entidades.Comentario;
import com.raul.henares.gestor_incidencias.Servicios.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:4200")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/getComments/{id}")
    public ResponseEntity<List<ComentarioDto>> listarComentarios(@PathVariable("id")Long id){
        try {
            List<ComentarioDto> respuesta = this.comentarioService.getPorIncidencia(id);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/addComment")
    public ResponseEntity<Map<String,String>> crearComentario(@RequestBody ComentarioDto comentario){
        try {
            this.comentarioService.crear(comentario);
            Map<String ,String> resp = new HashMap<>();
            resp.put("respuesta","Comentario creado");
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
