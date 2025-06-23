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

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Inventario;
import com.projecto_2.projecto_etapa2_beta.servicesBD1.InventarioServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InventarioRestControllersTest {
    
    @MockitoBean
    private InventarioServiceImpl inventarioServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Inventario> inventarioLista;

    @Test
    public void verInventarioTest() throws Exception{
        when (inventarioServiceImpl.findByAll()).thenReturn (inventarioLista);
        mockMvc.perform(get("/api/Inventario")
            .contentType (MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verunInventarioTest() {
        Inventario unInventario = new Inventario(1L,2,1);
        try {
            when (inventarioServiceImpl.findById(1L)).thenReturn (Optional.of (unInventario));
            mockMvc.perform(get("/api/Inventario/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch(Exception ex) {
            fail("El testing lanzó un error + ex.getMessage()");
        }
    }

    @Test
    public void InventarioNoExisteTest() throws Exception {
        when (inventarioServiceImpl.findById(10L)).thenReturn (Optional.empty());
        mockMvc.perform(get("/api/Inventario/10")
                .contentType (MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearInventarioTest() throws Exception {
        Inventario uninventario = new Inventario(null, 7,2);
        Inventario otroInventario = new Inventario(4L, 7,2);
        when (inventarioServiceImpl.save(any(Inventario.class))).thenReturn (otroInventario);
        mockMvc.perform(post("/api/Inventario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString (uninventario)))
                .andExpect(status().isCreated());
    }

    @Test
    public void modificarInventarioTest() throws Exception {
        Inventario InventarioExistente = new Inventario(1L,2,1);
        Inventario InventarioActualizado = new Inventario(1L,5,5);
        when(inventarioServiceImpl.findById(1L)).thenReturn(Optional.of(InventarioExistente));
        when(inventarioServiceImpl.save(any(Inventario.class))).thenReturn(InventarioActualizado);
        mockMvc.perform(put("/api/Inventario/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(InventarioActualizado)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarInventarioTest() throws Exception {
        Inventario InventarioAEliminar = new Inventario(1L,2,1);
        when(inventarioServiceImpl.findById(1L)).thenReturn(Optional.of(InventarioAEliminar));
        when(inventarioServiceImpl.delete(any(Inventario.class))).thenReturn(Optional.of(InventarioAEliminar));
        mockMvc.perform(delete("/api/Inventario/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void eliminarInventarioNoExistenteTest() throws Exception {
        when(inventarioServiceImpl.findById(99L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/Inventario/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); 
    }
}
