package com.blockbusterfake.demoT2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_alquiler")
@IdClass(DetalleAlquilerId.class)
public class DetalleAlquiler {
    @Id
    @ManyToOne
    @JoinColumn(name =  "id_alquiler")
    private Alquiler alquiler;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_pelicula")
    private Pelicula pelicula;

    @Column(nullable = false)
    private Integer cantidad;

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    //getter y settter

}

