package com.projecto_2.projecto_etapa2_beta.servicesBD1;

import java.util.List;

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Reporte;


public interface ReporteService {
    List<Reporte> findByAll();
    Reporte save(Reporte reporte);
    Reporte generarReporteCarritosActivos();
}
