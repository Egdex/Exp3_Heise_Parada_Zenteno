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

import com.projecto_2.projecto_etapa2_beta.entitiesBD1.Usuario;
import com.projecto_2.projecto_etapa2_beta.servicesBD1.UsuarioServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioRestControllersTest {

    @MockitoBean
    private UsuarioServiceImpl UsuarioServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Usuario> UsuarioLista;

    @Test
    public void verUsuarioTest() throws Exception{
        when (UsuarioServiceImpl.findByAll()).thenReturn (UsuarioLista);
        mockMvc.perform(get("/api/Usuario")
            .contentType (MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verunUsuarioTest() {
        Usuario unUsuario = new Usuario(1L,"12345678-9","LOL1@gmail.com","contrasena1");
        try {
            when (UsuarioServiceImpl.findById(1L)).thenReturn (Optional.of (unUsuario));
            mockMvc.perform(get("/api/Usuario/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch(Exception ex) {
            fail("El testing lanzó un error + ex.getMessage()");
        }
    }

    @Test
    public void UsuarioNoExisteTest() throws Exception {
        when (UsuarioServiceImpl.findById(10L)).thenReturn (Optional.empty());
        mockMvc.perform(get("/api/Usuario/10")
                .contentType (MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearUsuarioTest() throws Exception {
        Usuario unUsuario = new Usuario(null, "45345678-9","LOL2@gmail.com","contrasena12");
        Usuario otroUsuario = new Usuario(4L,"24545678-9","LOL4@gmail.com","contrasena1234");
        when (UsuarioServiceImpl.save(any(Usuario.class))).thenReturn (otroUsuario);
        mockMvc.perform(post("/api/Usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString (unUsuario)))
                .andExpect(status().isCreated());
    }

    @Test
    public void modificarUsuarioTest() throws Exception {
        Usuario UsuarioExistente = new Usuario(1L,"12345678-9","LOL1@gmail.com","contrasena1");
        Usuario UsuarioActualizado = new Usuario(1L,"12345678-9","LOL1@gmail.com","contrasena1");
        when(UsuarioServiceImpl.findById(1L)).thenReturn(Optional.of(UsuarioExistente));
        when(UsuarioServiceImpl.save(any(Usuario.class))).thenReturn(UsuarioActualizado);
        mockMvc.perform(put("/api/Usuario/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UsuarioActualizado)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarUsuarioTest() throws Exception {
        Usuario UsuarioAEliminar = new Usuario(1L,"12345678-9","LOL1@gmail.com","contrasena1");
        when(UsuarioServiceImpl.findById(1L)).thenReturn(Optional.of(UsuarioAEliminar));
        when(UsuarioServiceImpl.delete(any(Usuario.class))).thenReturn(Optional.of(UsuarioAEliminar));
        mockMvc.perform(delete("/api/Usuario/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void eliminarUsuarioNoExistenteTest() throws Exception {
        when(UsuarioServiceImpl.findById(99L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/Usuario/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); 
    }
}
