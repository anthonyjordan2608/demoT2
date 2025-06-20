package com.blockbusterfake.demoT2.repository;

import org.springframework.stereotype.Repository;

import com.blockbusterfake.demoT2.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
