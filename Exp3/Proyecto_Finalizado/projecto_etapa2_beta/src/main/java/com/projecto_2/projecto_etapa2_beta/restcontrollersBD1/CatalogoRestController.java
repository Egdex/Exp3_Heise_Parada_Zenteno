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

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Catalogo;
import com.projecto_2.projecto_etapa2_beta.servicesBD1.CatalogoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Catalogo", description = "Operaciones relacionadas con lod productos")
@RestController
@RequestMapping("api/productos")
public class CatalogoRestController {

    @Autowired
    private CatalogoService catalogoservice;

    @Operation(summary = "Obtener lista de productos", description = "Devuelve todos los productos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de productos retornada correctamente",
                 content = @Content(mediaType = "application/json",
                 schema = @Schema(implementation = Catalogo.class)))    
    @GetMapping
    public List<Catalogo> mostrarProducto(){
        return catalogoservice.findByAll();
    }

    @Operation(summary = "Obtener producto por ID", description = "Obtiene el detalle de un producto especifico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Catalogo.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })    
    @GetMapping("/{id}")
    public ResponseEntity<?> verCatalogo(@PathVariable Long id){
        Optional<Catalogo> optionalProducto = catalogoservice.findById(id);     //similar funcionalmente a select * from producto where condicion
        if (optionalProducto.isPresent()){
           return ResponseEntity.ok(optionalProducto.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "Crear un nuevo producto", description = "Crea un producto con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Catalogo.class)))    
    @PostMapping
    public ResponseEntity<Catalogo> crearCatalogo(@RequestBody Catalogo unCatalogo){
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogoservice.save(unCatalogo));
    }

    @Operation(summary = "Modifica un producto existente por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto modificado con éxito",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = Catalogo.class))),
        @ApiResponse(responseCode = "404", description = "Producto a modificar no encontrado",
                     content = @Content),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                     content = @Content)
    })    
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarProducto(@PathVariable Long id, @RequestBody Catalogo unProducto){
        Optional<Catalogo> optionalProducto = catalogoservice.findById(id);
        if (optionalProducto.isPresent()){
            Catalogo productoExiste = new Catalogo();
            productoExiste = optionalProducto.get();
            productoExiste.setNombre(unProducto.getNombre());
            productoExiste.setDescripcion(unProducto.getDescripcion());
            productoExiste.setPrecio(unProducto.getPrecio());
            Catalogo productoModificado = catalogoservice.save(productoExiste);
            return ResponseEntity.ok(productoModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Elimina un producto por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado con éxito (No Content)"),
        @ApiResponse(responseCode = "404", description = "Producto a eliminar no encontrado",
                     content = @Content) // No content for 404
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        Optional<Catalogo> optionalProducto = catalogoservice.findById(id);
        if (optionalProducto.isPresent()){
            catalogoservice.delete(optionalProducto.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
