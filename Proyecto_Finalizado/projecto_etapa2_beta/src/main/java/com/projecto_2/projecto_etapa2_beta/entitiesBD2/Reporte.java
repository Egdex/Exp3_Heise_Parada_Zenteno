package com.projecto_2.projecto_etapa2_beta.entitiesBD2;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "reportes")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reporte;

    private String tipo;
    private String descripcion;
    private LocalDateTime fecha;
    private String resultado;

    // Constructor vacío
    public Reporte() {
        this.fecha = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId_reporte() {
        return id_reporte;
    }

    public void setId_reporte(Long id_reporte) {
        this.id_reporte = id_reporte;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}

