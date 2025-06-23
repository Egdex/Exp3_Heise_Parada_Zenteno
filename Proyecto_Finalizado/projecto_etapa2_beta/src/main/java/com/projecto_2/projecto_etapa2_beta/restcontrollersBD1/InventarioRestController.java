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

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Inventario;
import com.projecto_2.projecto_etapa2_beta.servicesBD1.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Inventario", description = "Operaciones relacionadas con lod inventario")
@RestController
@RequestMapping("api/Inventario")
public class InventarioRestController {

    @Autowired
    private InventarioService inventarioservice;

    @Operation(summary = "Obtener lista de Inventario", description = "Devuelve todos los Inventario disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de Inventario retornada correctamente",
                 content = @Content(mediaType = "application/json",
                 schema = @Schema(implementation = Inventario.class)))    
    @GetMapping
    public List<Inventario> mostrarInventario(){
        return inventarioservice.findByAll();
    }

    @Operation(summary = "Obtener Inventario por ID", description = "Obtiene el detalle de un Inventario especifico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventario encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inventario.class))),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })    
    @GetMapping("/{id}")
    public ResponseEntity<?> verInventario(@PathVariable Long id){
        Optional<Inventario> optionalInventario = inventarioservice.findById(id);     //similar funcionalmente a select * from producto where condicion
        if (optionalInventario.isPresent()){
           return ResponseEntity.ok(optionalInventario.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "Crear un nuevo Inventario", description = "Crea un Inventario con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Inventario creado exitosamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inventario.class)))    
    @PostMapping
    public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario unInventario){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioservice.save(unInventario));
    }

    @Operation(summary = "Modifica un Inventario existente por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventario modificado con éxito",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = Inventario.class))),
        @ApiResponse(responseCode = "404", description = "Inventario a modificar no encontrado",
                     content = @Content),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                     content = @Content)
    })    
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarInventario(@PathVariable Long id, @RequestBody Inventario unInventario){
        Optional<Inventario> optionalInventario = inventarioservice.findById(id);
        if (optionalInventario.isPresent()){
            Inventario InventarioExiste = new Inventario();
            InventarioExiste = optionalInventario.get();
            InventarioExiste.setStock_actual(unInventario.getStock_actual());
            InventarioExiste.setStock_minimo(unInventario.getStock_minimo());
            Inventario InventarioModificado = inventarioservice.save(InventarioExiste);
            return ResponseEntity.ok(InventarioModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Elimina un Inventario por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Inventario eliminado con éxito (No Content)"),
        @ApiResponse(responseCode = "404", description = "Inventario a eliminar no encontrado",
                     content = @Content) // No content for 404
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInventario(@PathVariable Long id){
        Optional<Inventario> optionalInventario = inventarioservice.findById(id);
        if (optionalInventario.isPresent()){
            inventarioservice.delete(optionalInventario.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
