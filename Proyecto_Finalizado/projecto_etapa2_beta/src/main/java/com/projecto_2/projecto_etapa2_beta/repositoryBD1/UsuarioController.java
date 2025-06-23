package com.projecto_2.projecto_etapa2_beta.repositoryBD1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Usuario;
import com.projecto_2.projecto_etapa2_beta.servicesBD1.UsuarioService;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> list() {
        return service.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> verDETALLE(@PathVariable Long id) {
        Optional<Usuario> usuarioOpcional = service.findById(id);
        if (usuarioOpcional.isPresent()) {
            return ResponseEntity.ok(usuarioOpcional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario unUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Usuario unUsuario) {
        Optional<Usuario> usuarioOpcional = service.findById(id);
        if (usuarioOpcional.isPresent()) {
            Usuario usuarioExistente = usuarioOpcional.get();
            usuarioExistente.setRut(unUsuario.getRut());
            usuarioExistente.setCorreo(unUsuario.getCorreo());
            usuarioExistente.setContrasena(unUsuario.getContrasena());
            Usuario usuarioModificado = service.save(usuarioExistente);
            
            return ResponseEntity.ok(usuarioModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = service.findById(id);
        if (usuarioOptional.isPresent()) {
            service.delete(usuarioOptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
