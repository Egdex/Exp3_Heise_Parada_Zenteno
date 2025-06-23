package com.projecto_2.projecto_etapa2_beta.entitiesBD2;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_carrito;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "activo")
    private Boolean activo;
       // Si el carrito está abierto o ya fue procesado

       
    // ---- constructors ----
    // Default constructor
    public Carrito() {
    }

    // Constructor with usuarioId, initializes fechaCreacion to now and activo to true
    public Carrito(Long usuarioId) {
    this.usuarioId = usuarioId;
    this.fechaCreacion = LocalDateTime.now();
    this.activo = true;
    }   
    
    public Carrito(Long id_carrito, Long usuarioId, LocalDateTime fechaCreacion, Boolean activo) {
        this.id_carrito = id_carrito;
        this.usuarioId = usuarioId;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
    }


    // ---- getters and setters ----
    public Long getId_carrito() {
        return id_carrito;
    }

    public void setId_carrito(Long id_carrito) {
        this.id_carrito = id_carrito;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    
}
