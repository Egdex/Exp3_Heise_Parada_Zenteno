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

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Carrito;
import com.projecto_2.projecto_etapa2_beta.servicesBD1.CarritoService;


@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService service;

    @GetMapping
    public List<Carrito> listar() {
        return service.findByAll();
    }

    @GetMapping("/{id_carrito}")
    public ResponseEntity<Carrito> buscarPorId(@PathVariable Long id_carrito) {
        Optional<Carrito> carrito = service.findById(id_carrito);
        return carrito.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Carrito> crear(@RequestBody Carrito carrito) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(carrito));
    }

    @PutMapping("/{id_carrito}")
    public ResponseEntity<Carrito> actualizar(@PathVariable Long id_carrito, @RequestBody Carrito carritoNuevo) {
        Optional<Carrito> carritoOptional = service.findById(id_carrito);
        if (carritoOptional.isPresent()) {
            Carrito carritoExistente = carritoOptional.get();
            carritoExistente.setUsuarioId(carritoNuevo.getUsuarioId());
            carritoExistente.setFechaCreacion(carritoNuevo.getFechaCreacion());
            carritoExistente.setActivo(carritoNuevo.getActivo());
            return ResponseEntity.ok(service.save(carritoExistente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id_carrito}")
    public ResponseEntity<?> eliminar(@PathVariable Long id_carrito) {
        Optional<Carrito> carritoOptional = service.findById(id_carrito);
        if (carritoOptional.isPresent()) {
            service.delete(carritoOptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
