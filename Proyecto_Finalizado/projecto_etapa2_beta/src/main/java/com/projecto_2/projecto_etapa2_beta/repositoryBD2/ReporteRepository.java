package com.projecto_2.projecto_etapa2_beta.repositoryBD2;
import org.springframework.data.repository.CrudRepository;

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Reporte;


public interface ReporteRepository extends CrudRepository<Reporte, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar reportes por tipo o fecha
}