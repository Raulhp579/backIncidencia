package com.Raul.Henares.gestor_incidencias.Controladores;

import com.Raul.Henares.gestor_incidencias.Entidades.Comentario;
import com.Raul.Henares.gestor_incidencias.Servicios.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:4200")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/getComments/{id}")
    public List<Comentario> listarComentarios(@PathVariable("id")Long id){
        return this.comentarioService.getPorIncidencia(id);
    }

    @PostMapping("/addComment")
    public void crearComentario(@RequestBody Comentario comentario){
        this.comentarioService.crear(comentario);
    }
}
