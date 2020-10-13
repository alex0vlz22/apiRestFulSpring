package com.services.webServicesAndroid.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.webServicesAndroid.model.Estudiante;
import com.services.webServicesAndroid.repository.RepositoryEstudiante;

@RestController
public class RestControllerEstudiante {
	
	@Autowired
	private RepositoryEstudiante repo;
	
	@GetMapping("/estudiantes")
	public List<Estudiante> todos() {
		return repo.findAll();
	}
	
	@GetMapping("/estudiantes/{codigo}")
	public ResponseEntity<Estudiante> buscar(@PathVariable("codigo") String codigo) {
		Estudiante estu = repo.findByCodigo(codigo);
		if(estu == null)
			return ResponseEntity.notFound().build();
		else 
			return ResponseEntity.ok(estu);
	}

	@PostMapping("/estudiantes")
	public Estudiante crearEstudiante(@RequestBody Estudiante estudiante) {
		System.out.println(estudiante.toString());
		return repo.save(estudiante);
	}
	
	@PutMapping("/estudiantes")
	public ResponseEntity<Estudiante> actualizarEstudiante(@RequestBody  Estudiante estudiante) {
		Estudiante estu = repo.findByCodigo(estudiante.getCodigo());
		if(estu == null)
			return ResponseEntity.notFound().build();
		else
			estudiante.setId(estu.getId());
			repo.save(estudiante);
			return ResponseEntity.ok(estudiante);
	}
	
	@DeleteMapping("/estudiantes/{codigo}")
	public ResponseEntity<Estudiante> borrarEstudiante(@PathVariable("codigo") String codigo) {
		Estudiante estu = repo.findByCodigo(codigo);
		if(estu == null)
			return ResponseEntity.notFound().build();
		else
			repo.delete(estu);
			return ResponseEntity.ok(estu);
	}
}
