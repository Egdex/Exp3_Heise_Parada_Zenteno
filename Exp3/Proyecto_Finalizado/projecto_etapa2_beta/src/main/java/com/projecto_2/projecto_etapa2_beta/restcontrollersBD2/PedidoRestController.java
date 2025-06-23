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
import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Pedido;
import com.projecto_2.projecto_etapa2_beta.servicesBD2.Dto.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedido", description = "Operaciones pertinentes al los pedidos")
@RestController
@RequestMapping("api/pedidorest")
public class PedidoRestController {
    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Obtendra lista de pedidos", description = "Retornara todos los pedidos")
    @ApiResponse(responseCode = "200", description = "Pedidos retornados correctamente",
                content = @Content(mediaType = "aplication/json",
                schema = @Schema(implementation = Pedido.class)))
    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.findByAll();
    }

    @Operation (summary = "Obtener pedido segun el ID", description ="Obtendra el pedido especificado")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                     content = @Content(mediaType = "aplication/json",
                     schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtener(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.findById(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo pedido", description = "Crea un pedido con todos los datos requeridos")
    @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Pedido.class)))
    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedido));
    }

    @Operation(summary = "Modifica una parte del pedido existente segun su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido modificado con éxito",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = Carrito.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado",
                     content = @Content),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                     content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Pedido nuevo) {
        Optional<Pedido> optional = pedidoService.findById(id);
        if (optional.isPresent()) {
            Pedido existente = optional.get();
            existente.setId_carrito(nuevo.getId_carrito());
            existente.setUsuarioId(nuevo.getUsuarioId());
            existente.setEstado(nuevo.getEstado());
            existente.setTotal(nuevo.getTotal());
            existente.setFechaFactura(nuevo.getFechaFactura());
            return ResponseEntity.ok(pedidoService.save(existente));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Elimina un Pedido por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pedido eliminado con éxito "),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado",
                     content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Pedido> optional = pedidoService.findById(id);
        if (optional.isPresent()) {
            pedidoService.delete(optional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
