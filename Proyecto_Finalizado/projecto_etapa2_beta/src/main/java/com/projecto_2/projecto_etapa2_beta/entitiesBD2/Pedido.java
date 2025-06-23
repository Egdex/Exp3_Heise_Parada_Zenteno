package com.projecto_2.projecto_etapa2_beta.entitiesBD2;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    private Long id_carrito;
    private Long usuarioId;
    private String fechaPedido;
    private String estado;  
    private String fechaFactura;        // "pendiente", "pagado", etc.
    private Double total;          // Total del pedido
    // Fecha de la “factura”

    
    public Pedido() {
    }


    public Pedido(Long id_pedido, Long id_carrito, Long usuarioId, String fechaPedido, String estado,
            String fechaFactura, Double total) {
        this.id_pedido = id_pedido;
        this.id_carrito = id_carrito;
        this.usuarioId = usuarioId;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.fechaFactura = fechaFactura;
        this.total = total;
    }


    public Long getId_pedido() {
        return id_pedido;
    }


    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }


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


    public String getFechaPedido() {
        return fechaPedido;
    }


    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getFechaFactura() {
        return fechaFactura;
    }


    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }


    public Double getTotal() {
        return total;
    }


    public void setTotal(Double total) {
        this.total = total;
    }

    

    
    
}
