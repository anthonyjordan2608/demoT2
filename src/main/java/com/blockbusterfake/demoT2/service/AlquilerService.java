package com.blockbusterfake.demoT2.service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blockbusterfake.demoT2.model.Alquiler;
import com.blockbusterfake.demoT2.model.Cliente;
import com.blockbusterfake.demoT2.model.DetalleAlquiler;
import com.blockbusterfake.demoT2.model.EstadoAlquiler;
import com.blockbusterfake.demoT2.model.Pelicula;
import com.blockbusterfake.demoT2.repository.AlquilerRepository;
import com.blockbusterfake.demoT2.repository.ClienteRepository;
import com.blockbusterfake.demoT2.repository.DetalleAlquilerRepository;
import com.blockbusterfake.demoT2.repository.PeliculaRepository;

import jakarta.transaction.Transactional;

@Service
public class AlquilerService {
    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private DetalleAlquilerRepository detalleAlquilerRepository;

    @Transactional
    public Alquiler procesarAlquiler(Alquiler alquiler, List<DetalleAlquiler> detalles){
        double total = 0;
        for (DetalleAlquiler detalle : detalles){
            Pelicula pelicula = peliculaRepository.findById(detalle.getPelicula().getIdPelicula())
                .orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));
            
            if (pelicula.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para la pelicula: "+ pelicula.getTitulo());
            }

            pelicula.setStock(pelicula.getStock() - detalle.getCantidad());
            peliculaRepository.save(pelicula);

            total += detalle.getCantidad()*5.99;
        }

        alquiler.setFecha(LocalDateTime.now());
        alquiler.setTotal(total);
        alquiler.setEstado(EstadoAlquiler.ACTIVO);
        Alquiler alquilerGuardado = alquilerRepository.save(alquiler);

        for(DetalleAlquiler detalle: detalles){
            detalle.setAlquiler(alquilerGuardado);
            detalleAlquilerRepository.save(detalle);
        }
        return alquilerGuardado;
    }
    public List<Cliente> findAllClientes(){
        return clienteRepository.findAll();
    }
    public List<Pelicula> findAllPeliculas(){
        return peliculaRepository.findAll();
    }
}
