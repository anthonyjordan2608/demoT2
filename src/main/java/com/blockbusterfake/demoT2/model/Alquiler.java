package com.blockbusterfake.demoT2.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal; // Cambio clave para el campo total

@Entity
@Table(name = "alquileres")
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alquiler")
    private Integer idAlquiler;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    // SOLUCIÓN PRINCIPAL: Cambio de Double a BigDecimal
    @Column(nullable = false, columnDefinition = "DECIMAL(8,2)") // Definición explícita para PostgreSQL
    private BigDecimal total;  // Usamos BigDecimal para montos monetarios

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAlquiler estado = EstadoAlquiler.ACTIVO;

    // Constructores
    public Alquiler() {
    }

    public Alquiler(BigDecimal total, EstadoAlquiler estado) {
        this.total = total;
        this.estado = estado;
        this.fecha = LocalDateTime.now();
    }

    // Getters y Setters
    public Integer getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(Integer idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    // Método de conveniencia para setTotal con Double
    public void setTotal(Double total) {
        this.total = BigDecimal.valueOf(total);
    }

    public EstadoAlquiler getEstado() {
        return estado;
    }

    public void setEstado(EstadoAlquiler estado) {
        this.estado = estado;
    }

    // toString() para logging/debug
    @Override
    public String toString() {
        return "Alquiler{" +
                "id=" + idAlquiler +
                ", fecha=" + fecha +
                ", total=" + total +
                ", estado=" + estado +
                '}';
    }
}