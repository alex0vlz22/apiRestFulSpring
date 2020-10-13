package com.services.webServicesAndroid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.services.webServicesAndroid.model.Estudiante;

@Repository
public interface RepositoryEstudiante extends JpaRepository<Estudiante, Long>{
	Estudiante findByCodigo(String codigo);
}