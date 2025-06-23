package com.projecto_2.projecto_etapa2_beta.repositoryBD2;

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

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Pedido;
import com.projecto_2.projecto_etapa2_beta.servicesBD1.PedidoService;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {
     @Autowired
    private PedidoService service;

    @GetMapping
    public List<Pedido> listar() {
        return service.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtener(@PathVariable Long id) {
        Optional<Pedido> pedido = service.findById(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Pedido nuevo) {
        Optional<Pedido> optional = service.findById(id);
        if (optional.isPresent()) {
            Pedido existente = optional.get();
            existente.setId_carrito(nuevo.getId_carrito());
            existente.setUsuarioId(nuevo.getUsuarioId());
            existente.setEstado(nuevo.getEstado());
            existente.setTotal(nuevo.getTotal());
            existente.setFechaFactura(nuevo.getFechaFactura());
            return ResponseEntity.ok(service.save(existente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Pedido> optional = service.findById(id);
        if (optional.isPresent()) {
            service.delete(optional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
