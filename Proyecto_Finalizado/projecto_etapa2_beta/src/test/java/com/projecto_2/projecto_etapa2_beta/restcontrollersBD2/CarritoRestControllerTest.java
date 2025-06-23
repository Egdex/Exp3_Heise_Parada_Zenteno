package com.projecto_2.projecto_etapa2_beta.restcontrollersBD2;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Carrito;
import com.projecto_2.projecto_etapa2_beta.servicesBD2.Dto.CarritoServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@AutoConfigureMockMvc

public class CarritoRestControllerTest {
    @MockitoBean
    private CarritoServiceImpl carritoServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Carrito> listaCarrito;

    @Test
    public void listarTest() throws Exception{
        when (carritoServiceImpl.findByAll()).thenReturn (listaCarrito);
        mockMvc.perform(get("/api/carrito")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void buscarPorIdTest(){
        LocalDateTime fechaCreacion = LocalDateTime.of(2025, 6, 22, 10, 30, 0);
        Carrito unCarrito = new Carrito(1L, 1L, fechaCreacion, true);
        try{
            when (carritoServiceImpl.findById(1L)).thenReturn(Optional.of(unCarrito));
            mockMvc.perform(get("/api/carrito/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
            } catch(Exception ex) {
                fail("El testing lanzo un error" + ex.getMessage());
            }
    }

    @Test
    public void carritoNoExisteTest() throws Exception{
        when (carritoServiceImpl.findById(2L)).thenReturn (Optional.empty());
        mockMvc.perform(get("/api/carrito/2")
                .contentType (MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearCarritoTest() throws Exception {
        LocalDateTime fechaCreacion = LocalDateTime.of(2025, 3, 20, 10, 30, 0);
        Carrito unCarrito = new Carrito(3L, 3L, fechaCreacion, true);
        
        LocalDateTime fechaCreacionOtro = LocalDateTime.of(2025, 3, 20, 10, 30, 0);
        Carrito otroCarrito = new Carrito(4L, 4L, fechaCreacionOtro, false);
       
        when (carritoServiceImpl.save(any(Carrito.class))).thenReturn (otroCarrito);
        mockMvc.perform(post("/api/carrito")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString (unCarrito)))
                .andExpect(status().isCreated());
    }

    @Test
    public void editarCarritoTest() throws Exception {
        LocalDateTime fechaCreacion = LocalDateTime.of(2022, 6, 15, 20, 30, 0);
        Carrito carritoAnterior = new Carrito(5L, 3L, fechaCreacion, true);
        
        Carrito carritoNuevo = new Carrito(5L, 3L,fechaCreacion , false);

        when(carritoServiceImpl.findById(5L)).thenReturn(Optional.of(carritoAnterior));
        when(carritoServiceImpl.save(any(Carrito.class))).thenReturn(carritoNuevo);
        mockMvc.perform(put("/api/carrito/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carritoNuevo)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarCarritoTest() throws Exception {
        LocalDateTime fechaCreacion = LocalDateTime.of(2022, 6, 15, 20, 30, 0);
        Carrito carritoParaEliminar = new Carrito(5L, 3L, fechaCreacion, true);
        when(carritoServiceImpl.findById(5L)).thenReturn(Optional.of(carritoParaEliminar));
        when(carritoServiceImpl.delete(any(Carrito.class))).thenReturn(Optional.of(carritoParaEliminar));
        mockMvc.perform(delete("/api/carrito/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarCarritoNoExistenteTest() throws Exception {
        when(carritoServiceImpl.findById(25L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/carrito/25")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); 
    }




}
