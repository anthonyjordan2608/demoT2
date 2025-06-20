package com.blockbusterfake.demoT2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blockbusterfake.demoT2.model.DetalleAlquiler;
import com.blockbusterfake.demoT2.model.DetalleAlquilerId;

public interface DetalleAlquilerRepository extends JpaRepository<DetalleAlquiler, DetalleAlquilerId>{

    
    
}
