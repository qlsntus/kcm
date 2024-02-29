package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Jo;

public interface JoRepository 
                   extends JpaRepository<Jo, Integer>{
	
}
