package com.raul.henares.gestor_incidencias.Controladores;

import com.raul.henares.gestor_incidencias.Dtos.ModificarUsuarioDto;
import com.raul.henares.gestor_incidencias.Dtos.UsuarioDto;
import com.raul.henares.gestor_incidencias.Entidades.Usuario;
import com.raul.henares.gestor_incidencias.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
//seguramente necesite ajustar el Cross
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/getUsers")
    public ResponseEntity<List<UsuarioDto>> listarUsuarios(){
        try {
            List<UsuarioDto> respuesta = this.usuarioService.getAll();
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioPorId(@PathVariable("id") Long idUsuario){
        try {
            UsuarioDto respuesta = this.usuarioService.getById(idUsuario);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/addUser")
    public ResponseEntity<String> crearUsuario(@RequestBody UsuarioDto usuario){
        try {
            this.usuarioService.crear(usuario);
            return ResponseEntity.ok("usuario creado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/updateUser") //cambiar en el front
    public ResponseEntity<String> actualizarUsuario(@RequestBody ModificarUsuarioDto dto){
        try {
            this.usuarioService.modificarUsuario(dto);
            return ResponseEntity.ok("usuario modificado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getByName/{nombre}")
    public ResponseEntity<UsuarioDto> buscarPorNombre(@PathVariable("nombre") String nombre){
        try {
            UsuarioDto respuesta = this.usuarioService.getPorNombre(nombre);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
