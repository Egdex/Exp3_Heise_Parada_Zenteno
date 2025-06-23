package com.projecto_2.projecto_etapa2_beta.servicesBD2.Dto;




public class carrito {
    private Long id_carrito;
    private Long usuarioId;
    private String fechaCreacion;
    private Boolean activo;

    public Long getId_carrito() { return id_carrito; }
    public void setId_carrito(Long id_carrito) { this.id_carrito = id_carrito; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
