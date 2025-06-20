package com.blockbusterfake.demoT2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blockbusterfake.demoT2.model.Alquiler;

@Repository
public interface AlquilerRepository  extends JpaRepository<Alquiler,Integer>{

    
}
