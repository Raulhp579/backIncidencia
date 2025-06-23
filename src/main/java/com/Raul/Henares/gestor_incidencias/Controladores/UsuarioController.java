package com.Raul.Henares.gestor_incidencias.Controladores;

import com.Raul.Henares.gestor_incidencias.Entidades.Usuario;
import com.Raul.Henares.gestor_incidencias.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Usuario> listarUsuarios(){
        return this.usuarioService.getAll();
    }

    @GetMapping("/getUser/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable("id") Long idUsuario){
        return this.usuarioService.getById(idUsuario);
    }

    @PostMapping("/addUser")
    public void crearUsuario(@RequestBody Usuario usuario){
        this.usuarioService.crear(usuario);
    }

    @PutMapping("/updateUser/{idUser}/{cambio}/{valor}")
    public <T> void actualizarUsuario(@PathVariable("idUser")Long idUsuario,
                                      @PathVariable("cambio")String cambio,
                                      @PathVariable("valor")T valor){

        this.usuarioService.modificarUsuario(idUsuario,cambio,valor);
    }

    @GetMapping("/getByName/{nombre}")
    public Usuario buscarPorNombre(@PathVariable("nombre") String nombre){
        return this.usuarioService.getPorNombre(nombre);
    }
}
