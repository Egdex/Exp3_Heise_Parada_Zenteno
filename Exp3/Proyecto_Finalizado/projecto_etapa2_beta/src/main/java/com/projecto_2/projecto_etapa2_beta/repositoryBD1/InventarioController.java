package com.projecto_2.projecto_etapa2_beta.repositoryBD1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Inventario;
import com.projecto_2.projecto_etapa2_beta.servicesBD1.InventarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService service;

    @GetMapping
    public List <Inventario> list() {
        return service.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> verDetalle(@PathVariable Long id) {
        Optional<Inventario> productoOpcional = service.findById(id);
        if (productoOpcional.isPresent()) {
            return ResponseEntity.ok(productoOpcional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Inventario> crear(@RequestBody Inventario unProducto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unProducto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Inventario unProducto) {
        Optional<Inventario> productoOpcional = service.findById(id);
        if (productoOpcional.isPresent()) {
            Inventario productoExistente = productoOpcional.get();
            productoExistente.setStock_actual(unProducto.getStock_actual());
            productoExistente.setStock_minimo(unProducto.getStock_minimo());
            Inventario productomodificado = service.save(productoExistente);
            return ResponseEntity.ok(productomodificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<?> eliminar(@PathVariable Long id) {
            Inventario unProducto = new Inventario();
            unProducto.setId_producto(id);
            Optional<Inventario> productoOptional = service.delete(unProducto);
            if (productoOptional.isPresent()) {
                return ResponseEntity.ok(productoOptional.orElseThrow());
            }
            return ResponseEntity.notFound().build();
        }
    }