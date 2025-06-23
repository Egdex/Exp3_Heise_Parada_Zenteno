package com.projecto_2.projecto_etapa2_beta.restcontrollersBD1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Usuario;
import com.projecto_2.projecto_etapa2_beta.servicesBD1.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuario", description = "Operaciones relacionadas con lod Usuario")
@RestController
@RequestMapping("api/Usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioService Usuarioservice;

    @Operation(summary = "Obtener lista de Usuario", description = "Devuelve todos los Usuario disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de Usuario retornada correctamente",
                 content = @Content(mediaType = "application/json",
                 schema = @Schema(implementation = Usuario.class)))    
    @GetMapping
    public List<Usuario> mostrarProducto(){
        return Usuarioservice.findByAll();
    }

    @Operation(summary = "Obtener Usuario por ID", description = "Obtiene el detalle de un Usuario especifico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })    
    @GetMapping("/{id}")
    public ResponseEntity<?> verUsuario(@PathVariable Long id){
        Optional<Usuario> optionalUsuario = Usuarioservice.findById(id);     //similar funcionalmente a select * from producto where condicion
        if (optionalUsuario.isPresent()){
           return ResponseEntity.ok(optionalUsuario.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "Crear un nuevo Usuario", description = "Crea un Usuario con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))    
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario unUsuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(Usuarioservice.save(unUsuario));
    }

    @Operation(summary = "Modifica un Usuario existente por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario modificado con éxito",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario a modificar no encontrado",
                     content = @Content),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                     content = @Content)
    })    
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Long id, @RequestBody Usuario unUsuario){
        Optional<Usuario> optionalUsuario = Usuarioservice.findById(id);
        if (optionalUsuario.isPresent()){
            Usuario UsuarioExiste = new Usuario();
            UsuarioExiste = optionalUsuario.get();
            UsuarioExiste.setRut(unUsuario.getRut());
            UsuarioExiste.setCorreo(unUsuario.getCorreo());
            UsuarioExiste.setContrasena(unUsuario.getContrasena());
            Usuario productoModificado = Usuarioservice.save(UsuarioExiste);
            return ResponseEntity.ok(productoModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Elimina un Usuario por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado con éxito (No Content)"),
        @ApiResponse(responseCode = "404", description = "Producto a eliminar no encontrado",
                     content = @Content) // No content for 404
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        Optional<Usuario> optionalUsuario = Usuarioservice.findById(id);
        if (optionalUsuario.isPresent()){
            Usuarioservice.delete(optionalUsuario.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
