package com.projecto_2.projecto_etapa2_beta.servicesBD1;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Carrito;
import com.projecto_2.projecto_etapa2_beta.entitiesBD2.Reporte;
import com.projecto_2.projecto_etapa2_beta.repositoryBD2.ReporteRepository;

@Service
public class ReporteServiceImpl implements ReporteService {

    /**
     * Clase que implementa la lógica de negocio para generar reportes.
     * Utiliza un repositorio para acceder a los datos y un RestTemplate para
     * realizar peticiones HTTP a otros microservicios.
     */
@Autowired
    private ReporteRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Reporte> findByAll() {
        return (List<Reporte>) repository.findAll();
    }

    @Override
    public Reporte save(Reporte reporte) {
        return repository.save(reporte);
    }

    @Override
    public Reporte generarReporteCarritosActivos() {
        String url = "http://localhost:8080/api/carrito"; // cambia el puerto si es necesario
        Carrito[] carritos = restTemplate.getForObject(url, Carrito[].class);

        long activos = Arrays.stream(carritos)
                .filter(c -> c.getActivo() != null && c.getActivo())
                .count();

        Reporte reporte = new Reporte();
        reporte.setTipo("Carritos Activos");
        reporte.setDescripcion("Cantidad de carritos con estado activo = true");
        reporte.setResultado("Total activos: " + activos);
        reporte.setFecha(LocalDateTime.now());

        return repository.save(reporte);
    }
}
