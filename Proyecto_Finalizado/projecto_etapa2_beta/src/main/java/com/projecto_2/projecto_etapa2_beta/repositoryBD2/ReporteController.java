package com.projecto_2.projecto_etapa2_beta.repositoryBD2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Reporte;
import com.projecto_2.projecto_etapa2_beta.servicesBD2.Dto.ReporteService;

@RestController
@RequestMapping("api/reporte")
public class ReporteController {
    
    @Autowired
    private ReporteService service;

    @GetMapping
    public List<Reporte> listarReportes() {
        return service.findByAll();
    }

    @GetMapping("/carritos-activos")
    public Reporte generarReporteCarritosActivos() {
        return service.generarReporteCarritosActivos();
    }
}
