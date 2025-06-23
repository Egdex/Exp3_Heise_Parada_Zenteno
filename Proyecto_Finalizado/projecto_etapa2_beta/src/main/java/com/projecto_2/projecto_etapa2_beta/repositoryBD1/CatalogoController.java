package com.projecto_2.projecto_etapa2_beta.repositoryBD1;

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

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Catalogo;
import com.projecto_2.projecto_etapa2_beta.servicesBD1.CatalogoService;

@RestController
@RequestMapping("api/catalogo")
public class CatalogoController {

    @Autowired
    private CatalogoService service;

    @GetMapping
    public List <Catalogo> list() {
        return service.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalogo> verDETALLE(@PathVariable Long id) {
        Optional<Catalogo> productoOpcional = service.findById(id);
        if (productoOpcional.isPresent()) {
            return ResponseEntity.ok(productoOpcional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
 
    @PostMapping
    public ResponseEntity<Catalogo> crear(@RequestBody Catalogo unProducto) {
       
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unProducto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Catalogo unProducto) {
        Optional<Catalogo> productoOpcional = service.findById(id);
        if (productoOpcional.isPresent()) {
            Catalogo productoExistente = productoOpcional.get();
            productoExistente.setNombre(unProducto.getNombre());
            productoExistente.setDescripcion(unProducto.getDescripcion());
            productoExistente.setPrecio(unProducto.getPrecio());
            Catalogo productomodificado = service.save(productoExistente);

            return ResponseEntity.ok(productomodificado);
        }

        return ResponseEntity.notFound().build();   
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<?> eliminar(@PathVariable Long id) {
            Catalogo unProducto = new Catalogo();
            unProducto.setId(id);
            Optional<Catalogo> productoOptional = service.delete(unProducto);
            if (productoOptional.isPresent()) {
                return ResponseEntity.ok(productoOptional.orElseThrow());
            }
            return ResponseEntity.notFound().build();
        }
}
