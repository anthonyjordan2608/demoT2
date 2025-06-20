package com.blockbusterfake.demoT2.model;

import java.io.Serializable;
import java.util.Objects;

public class DetalleAlquilerId implements Serializable{
    private Integer alquiler;
    
    private Integer pelicula;

    public DetalleAlquilerId(){}
    
    public DetalleAlquilerId(Integer alquiler, Integer pelicula){
        this.alquiler = alquiler;
        this.pelicula = pelicula;
    }

    public Integer getAlquiler(){
        return alquiler;
    }

    public void setAlquiler(Integer alquiler){
        this.alquiler = alquiler;
    }

    public Integer getPelicula(){
        return pelicula;
    }

    public void setPelicula(Integer pelicula){
        this.pelicula = pelicula;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() !=o.getClass()) return false;
        DetalleAlquilerId that = (DetalleAlquilerId) o;
        return Objects.equals(alquiler, that.alquiler)&&
               Objects.equals(pelicula, that.pelicula);
    }

    @Override
    public int hashCode(){
        return Objects.hash(alquiler, pelicula);
    }
}
