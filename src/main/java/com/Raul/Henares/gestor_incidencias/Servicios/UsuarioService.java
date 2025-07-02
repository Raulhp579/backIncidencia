package com.raul.henares.gestor_incidencias.Servicios;

import com.raul.henares.gestor_incidencias.Dtos.ModificarUsuarioDto;
import com.raul.henares.gestor_incidencias.Dtos.UsuarioDto;
import com.raul.henares.gestor_incidencias.Entidades.Rol;
import com.raul.henares.gestor_incidencias.Entidades.Usuario;
import com.raul.henares.gestor_incidencias.Repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public static UsuarioDto getUsuarioDto(Usuario usuario){
        UsuarioDto dto = new UsuarioDto();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setContrasenya(usuario.getContrasenya());
        dto.setRol(usuario.getRol());
        return dto;
    }

    public List<UsuarioDto> getAll(){
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        List<UsuarioDto> dtos = new ArrayList<>();
        for (Usuario usuario : usuarios){
            UsuarioDto dto = getUsuarioDto(usuario);
            dtos.add(dto);
        }
        return dtos;
    }

    public UsuarioDto getById(Long id){
        Usuario usuario = this.usuarioRepository.getReferenceById(id);
        return getUsuarioDto(usuario);
    }

    public Usuario crear(UsuarioDto dto){
        String nombre = dto.getNombre();
        String email = dto.getEmail();
        String contrasenya = dto.getContrasenya();
        Rol rol = dto.getRol();
        Usuario usuario = new Usuario(nombre,email,contrasenya,rol);
        return this.usuarioRepository.save(usuario);
    }

    public Usuario modificarUsuario(ModificarUsuarioDto dto) {
        Usuario usuario = usuarioRepository.getReferenceById(dto.getIdUsuario());

        switch (dto.getCambio().toLowerCase()) {
            case "nombre":
                usuario.setNombre(dto.getValor());
                break;
            case "email":
                usuario.setEmail(dto.getValor());
                break;
            case "contraseña":
                usuario.setContrasenya(dto.getValor());
                break;
            case "rol":
                usuario.setRol(Rol.valueOf(dto.getValor().toUpperCase()));
                break;
        }

        return usuarioRepository.save(usuario);
    }


    public UsuarioDto getPorNombre(String nombre){
        Usuario usuario = this.usuarioRepository.findByNombre(nombre);
        return getUsuarioDto(usuario);
    }

    public List<UsuarioDto> buscarPorRol(Rol rol){
        List<Usuario> usuarios = this.usuarioRepository.findByRol(rol);
        List<UsuarioDto> usuarioDtos = new ArrayList<>();

        for (Usuario usuario : usuarios){
            UsuarioDto dto = getUsuarioDto(usuario);
            usuarioDtos.add(dto);
        }
        return usuarioDtos;
    }
}
