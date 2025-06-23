package com.projecto_2.projecto_etapa2_beta.restcontrollersBD1;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Catalogo;
import com.projecto_2.projecto_etapa2_beta.servicesBD1.CatalogoServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CatalogoRestControllersTest {

    @MockitoBean
    private CatalogoServiceImpl catalogoServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Catalogo> CatalogoLista;

    @Test
    public void verProductosTest() throws Exception{
        when (catalogoServiceImpl.findByAll()).thenReturn (CatalogoLista);
        mockMvc.perform(get("/api/productos")
            .contentType (MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verunProductoTest() {
        Catalogo unProducto = new Catalogo(1L, "antonio vanderas", "Pfresco y masculino", 20000,"hombres");
        try {
            when (catalogoServiceImpl.findById(1L)).thenReturn (Optional.of (unProducto));
            mockMvc.perform(get("/api/productos/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch(Exception ex) {
            fail("El testing lanzó un error + ex.getMessage()");
        }
    }

    @Test
    public void productoNoExisteTest() throws Exception {
        when (catalogoServiceImpl.findById(10L)).thenReturn (Optional.empty());
        mockMvc.perform(get("/api/productos/10")
                .contentType (MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearProductoTest() throws Exception {
        Catalogo unProducto = new Catalogo(null, "pan amasado", "Producto chileno", 380,"hombre");
        Catalogo otroProducto = new Catalogo(4L, "pan amasado integral", "producto chileno", 350,"hombre");
        when (catalogoServiceImpl.save(any(Catalogo.class))).thenReturn (otroProducto);
        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString (unProducto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void modificarProductoTest() throws Exception {
        Catalogo productoExistente = new Catalogo(1L, "antonio vanderas", "Pfresco y masculino", 20000,"hombres");
        Catalogo productoActualizado = new Catalogo(1L, "Antonio vanderas", "Fresco y masculino", 20000," para hombres");
        when(catalogoServiceImpl.findById(1L)).thenReturn(Optional.of(productoExistente));
        when(catalogoServiceImpl.save(any(Catalogo.class))).thenReturn(productoActualizado);
        mockMvc.perform(put("/api/productos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productoActualizado)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarProductoTest() throws Exception {
        Catalogo productoAEliminar = new Catalogo(1L, "antonio vanderas", "Pfresco y masculino", 20000,"hombres");
        when(catalogoServiceImpl.findById(1L)).thenReturn(Optional.of(productoAEliminar));
        when(catalogoServiceImpl.delete(any(Catalogo.class))).thenReturn(Optional.of(productoAEliminar));
        mockMvc.perform(delete("/api/productos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void eliminarProductoNoExistenteTest() throws Exception {
        when(catalogoServiceImpl.findById(99L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/productos/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); 
    }
}
