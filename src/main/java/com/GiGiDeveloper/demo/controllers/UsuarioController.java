package com.GiGiDeveloper.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GiGiDeveloper.demo.models.UsuarioModel;
import com.GiGiDeveloper.demo.services.UsuarioService;

@RestController
@RequestMapping("/usuario") // en que direccion del servidor se va a activar está api
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping() // Cuando venga un get que ejecute esto
    public ArrayList<UsuarioModel> getUsuarios(){
        return usuarioService.getUsuarios();
    }

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping( path = "/{id}") //http://localhost:8080/usuario/1
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return this.usuarioService.obtenerPorId(id);
    }

    @GetMapping("/query") //http://localhost:8080/usuario/query?prioridad=5
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad) {
        return this.usuarioService.obtenerPorPrioridad(prioridad);
    }

    @DeleteMapping( path = "/{id}")  // POSTMAN DELETE http://localhost:8080/usuario/1
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = usuarioService.eliminarUsuario(id);
        if (ok) {
            return "Se eliminó el usuario con id: "+id;
        } else {
            return "No pudo eliminar el usuario con id: "+id;
        }
    }

}
