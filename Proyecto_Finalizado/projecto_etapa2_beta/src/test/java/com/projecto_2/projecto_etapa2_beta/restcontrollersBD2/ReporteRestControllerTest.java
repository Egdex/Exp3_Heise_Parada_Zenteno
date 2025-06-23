package com.projecto_2.projecto_etapa2_beta.restcontrollersBD2;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Reporte;
import com.projecto_2.projecto_etapa2_beta.servicesBD2.Dto.ReporteServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ReporteRestControllerTest {
    @MockitoBean
    private ReporteServiceImpl reporteServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    private List<Reporte> listaReporte;

    @Test
    public void listarReporteTest() throws Exception{
        when (reporteServiceImpl.findByAll()).thenReturn (listaReporte);
        mockMvc.perform(get("/api/reporte")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void generarReporteCarritosActivos_DeberiaRetornarReporte() throws Exception {
        Reporte reporte = new Reporte();
        reporte.setId_reporte(1L);
        reporte.setTipo("Carritos Activos");
        reporte.setDescripcion("Cantidad de carritos con estado activo = true");
        reporte.setResultado("Total activos: 3");
        reporte.setFecha(LocalDateTime.now());
        when(reporteServiceImpl.generarReporteCarritosActivos()).thenReturn(reporte);
        mockMvc.perform(get("/api/reporte/carritos-activos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("Carritos Activos"))
                .andExpect(jsonPath("$.resultado").value("Total activos: 3"));
    }
}
