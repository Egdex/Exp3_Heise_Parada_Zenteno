package com.projecto_2.projecto_etapa2_beta.restcontrollersBD2;

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

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Carrito;
import com.projecto_2.projecto_etapa2_beta.servicesBD2.Dto.CarritoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Carrito", description = "Operaciones vinculadas al carrito")
@RestController
@RequestMapping("api/carro")
public class CarritoRestController {

    @Autowired
    private CarritoService carritoService;

    @Operation(summary = "Obtener lista de carritos", description = "Devolvera todos los carritos")
    @ApiResponse(responseCode = "200", description = "Carritos retornados correctamente",
                 content = @Content(mediaType = "aplication/json",
                 schema = @Schema(implementation = Carrito.class))) 
    @GetMapping
    public List<Carrito> listarCarrito(){
        return carritoService.findByAll();
    }

    @Operation (summary = "Obtener carrito segun el ID", description ="Obtendra el detalle del carrito especificado")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Carrito encontrado",
                     content = @Content(mediaType = "aplication/json",
                     schema = @Schema(implementation = Carrito.class))),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    })
    @GetMapping("/{id_carrito}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id_carrito) {
        Optional<Carrito> carrito = carritoService.findById(id_carrito);
        return carrito.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo carrito", description = "Crea un carrito con todos los datos requeridos")
    @ApiResponse(responseCode = "201", description = "Carrito creado exitosamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Carrito.class)))
    @PostMapping
    public ResponseEntity<Carrito> crear(@RequestBody Carrito carrito) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carritoService.save(carrito));
    }

    @Operation(summary = "Modifica una parte del carrito existente por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carrito modificado con éxito",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = Carrito.class))),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado",
                     content = @Content),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                     content = @Content)
    })  
    @PutMapping("/{id_carrito}")
    public ResponseEntity<Carrito> actualizar(@PathVariable Long id_carrito, @RequestBody Carrito carritoNuevo) {
        Optional<Carrito> carritoOptional = carritoService.findById(id_carrito);
        if (carritoOptional.isPresent()) {
            Carrito carritoExistente = carritoOptional.get();
            carritoExistente.setUsuarioId(carritoNuevo.getUsuarioId());
            carritoExistente.setFechaCreacion(carritoNuevo.getFechaCreacion());
            carritoExistente.setActivo(carritoNuevo.getActivo());
            return ResponseEntity.ok(carritoService.save(carritoExistente));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Elimina un carrito por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Carrito eliminado con éxito "),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado",
                     content = @Content)
    })
    @DeleteMapping("/{id_carrito}")
    public ResponseEntity<?> eliminar(@PathVariable Long id_carrito) {
        Optional<Carrito> carritoOptional = carritoService.findById(id_carrito);
        if (carritoOptional.isPresent()) {
            carritoService.delete(carritoOptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }





}
