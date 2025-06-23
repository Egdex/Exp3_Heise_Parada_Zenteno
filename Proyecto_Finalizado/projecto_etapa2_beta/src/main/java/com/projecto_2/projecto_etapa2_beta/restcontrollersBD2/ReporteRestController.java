package com.projecto_2.projecto_etapa2_beta.restcontrollersBD2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Reporte;
import com.projecto_2.projecto_etapa2_beta.servicesBD2.Dto.ReporteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("api/reporterest")
public class ReporteRestController {
    @Autowired
    private ReporteService reporteService;

    @Operation(summary = "Obtendra lista de reportes", description = "Retornara todos los reportes")
    @ApiResponse(responseCode = "200", description = "Pedidos retornados correctamente",
                content = @Content(mediaType = "aplication/json",
                schema = @Schema(implementation = Reporte.class)))
    @GetMapping
    public List<Reporte> listarReportes() {
        return reporteService.findByAll();
    }

    @Operation(summary = "Obtendra un reporte sobre los Carritos Activos", description = "Retornara un detalle de carritos Activos")
    @ApiResponse(responseCode = "200", description = "Reporte de carritos retornado correctamente",
                content = @Content(mediaType = "aplication/json",
                schema = @Schema(implementation = Reporte.class)))
    @GetMapping("/carritos-activos")
    public Reporte generarReporteCarritosActivos() {
        return reporteService.generarReporteCarritosActivos();
    }
}
