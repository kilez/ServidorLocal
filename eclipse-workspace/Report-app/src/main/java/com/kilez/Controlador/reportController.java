package com.kilez.Controlador;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kilez.Service.reportService;
import com.kilez.entity.Report;

@CrossOrigin({"*"})
@RestController
public class reportController {

	
	@Autowired
	private reportService reportService;
	
	
	@GetMapping
	public ResponseEntity<?> listar() {
		
		return ResponseEntity.ok().body(reportService.findAll());
	}
	
	
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?>verFoto(@PathVariable Long id){
		
		Optional<Report> o = reportService.findById(id);
		
		if(o.get().getFoto()==null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource imagen = new ByteArrayResource(o.get().getFoto());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
	}
	
	
	@PostMapping
	public ResponseEntity<?> Crear(@RequestBody Report report ) {
		Report reportDb = reportService.save(report);
		return ResponseEntity.status(HttpStatus.CREATED).body(reportDb);
	}
	
	
	
	@PostMapping("/crea-con-imagen")
	public ResponseEntity<?> CrearconImagen(Report report, @RequestParam MultipartFile archivo ) throws IOException {
		
		if(!archivo.isEmpty()) {
			report.setFoto(archivo.getBytes());
		}

		Report reportDb = reportService.save(report);
		return ResponseEntity.status(HttpStatus.CREATED).body(reportDb);
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<?> Editar(@RequestBody Report report, @PathVariable Long id){

		Optional<Report> o = reportService.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Report reportDb = o.get();
		
		//Report reportDb = reportService.save(report);
		
		reportDb.setPart_class(report.getPart_class());
		reportDb.setSerial(report.getSerial());
		reportDb.setAging(report.getAging());
		reportDb.setCategory(report.getCategory());
		reportDb.setOpcode(report.getOpcode());
		reportDb.setComment(report.getComment());
		reportDb.setSymptom(report.getSymptom());
		reportDb.setDefect(report.getDefect());
		reportDb.setLocation(report.getLocation());
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(reportService.save(reportDb));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		Optional<Report> o = reportService.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(o.get());
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> Borrar(@PathVariable Long id){
		Optional<Report> o = reportService.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		reportService.delateById(id);
		
		return ResponseEntity.ok(o.get());
	}
}
