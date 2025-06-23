package com.projecto_2.projecto_etapa2_beta.restcontrollersBD2;

import static org.mockito.Mockito.when;

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
import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Pedido;
import com.projecto_2.projecto_etapa2_beta.servicesBD2.Dto.PedidoServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoRestControllerTest {
    @MockitoBean
    private PedidoServiceImpl pedidoServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    private List<Pedido> listaPedido;

    @Test
    public void listarReporteTest() throws Exception{
        when (pedidoServiceImpl.findByAll()).thenReturn (listaPedido);
        mockMvc.perform(get("/api/pedido")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void buscarReportePorIdTest(){
        Pedido unPedido = new Pedido(1L, 1L, 1L, 
        "22/06/25", "activo", "10/06/25", 10990.85);
        try{
            when (pedidoServiceImpl.findById(1L)).thenReturn(Optional.of(unPedido));
            mockMvc.perform(get("/api/pedido/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
            } catch(Exception ex) {
                fail("El testing lanzo un error" + ex.getMessage());
            }
        }

    @Test
    public void pedidoNoExisteTest() throws Exception {
        when (pedidoServiceImpl.findById(50L)).thenReturn (Optional.empty());
        mockMvc.perform(get("/api/pedido/50")
                .contentType (MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearPedidoTest() throws Exception {
        Pedido unPedido = new Pedido(2L, 2L, 2L, 
        "15/03/25", "activo", "01/03/25", 5990.75);
        Pedido otroPedido = new Pedido(3L, 3L, 3L, 
        "15/03/25", "activo", "01/03/25", 2990.5);
        when (pedidoServiceImpl.save(any(Pedido.class))).thenReturn (otroPedido);
        mockMvc.perform(post("/api/pedido")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString (unPedido)))
                .andExpect(status().isCreated());
    }

    @Test
    public void modificarPedidoTest() throws Exception {
        Pedido pedido = new Pedido(2L, 2L, 2L, 
        "15/03/25", "activo", "01/03/25", 5990.75);
        Pedido pedidoActualizado = new Pedido(2L, 2L, 2L, 
        "15/03/25", "activo", "01/03/25", 5000.0);
        when(pedidoServiceImpl.findById(2L)).thenReturn(Optional.of(pedido));
        when(pedidoServiceImpl.save(any(Pedido.class))).thenReturn(pedidoActualizado);
        mockMvc.perform(put("/api/pedido/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedidoActualizado)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarPedidoTest() throws Exception {
        Pedido pedidoParaEliminar = new Pedido(4L, 4L, 4L, 
        "09/02/25", "inactivo", "02/01/25", 50000.00);
        when(pedidoServiceImpl.findById(4L)).thenReturn(Optional.of(pedidoParaEliminar));
        when(pedidoServiceImpl.delete(any(Pedido.class))).thenReturn(Optional.of(pedidoParaEliminar));
        mockMvc.perform(delete("/api/pedido/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarPedidoNoExistenteTest() throws Exception {
        when(pedidoServiceImpl.findById(35L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/pedido/35")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); 
    }
}
