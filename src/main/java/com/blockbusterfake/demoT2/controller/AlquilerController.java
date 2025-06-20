package com.blockbusterfake.demoT2.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.blockbusterfake.demoT2.model.Alquiler;
import com.blockbusterfake.demoT2.model.DetalleAlquiler;
import com.blockbusterfake.demoT2.model.EstadoAlquiler;
import com.blockbusterfake.demoT2.model.Pelicula;
import com.blockbusterfake.demoT2.service.AlquilerService;

//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model; 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/alquileres")
public class AlquilerController {

    @Autowired
    private AlquilerService alquilerService;

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("alquiler", new Alquiler());
        model.addAttribute("detalles", new ArrayList<DetalleAlquiler>());
        model.addAttribute("clientes", alquilerService.findAllClientes());
        model.addAttribute("peliculas", alquilerService.findAllPeliculas());
        return "alquiler/formulario";
    }
    
    @PostMapping("/procesar")
    public String procesarAlquiler(
        @ModelAttribute Alquiler alquiler,
        @RequestParam List<Integer> peliculas,
        @RequestParam List<Integer> cantidades,
        Model model) {

            try{
                List<DetalleAlquiler> detalles = new ArrayList<>();
                for(int i=0;i < peliculas.size(); i++){
                    if (cantidades.get(i)>0) {
                        DetalleAlquiler detalle = new DetalleAlquiler();

                        Pelicula pelicula = new Pelicula();
                        pelicula.setIdPelicula(peliculas.get(i));
                        detalle.setPelicula(pelicula);
                        detalle.setCantidad(cantidades.get(i));
                        detalles.add(detalle);
                    }
                }
                alquiler.setFecha(LocalDateTime.now());
                alquiler.setEstado(EstadoAlquiler.ACTIVO);

                alquilerService.procesarAlquiler(alquiler, detalles);

                model.addAttribute("mensaje", "!Alquiler registrado");
            } catch(Exception e){
                model.addAttribute("error", "Error al procesar el alquiler: "+ e.getMessage());             
            }

        
        return mostrarFormulario(model);
    }
    
}
    
        
    

