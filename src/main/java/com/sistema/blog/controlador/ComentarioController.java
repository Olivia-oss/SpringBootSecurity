package com.sistema.blog.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.dto.ComentarioDTO;

import com.sistema.blog.servicio.ComentatarioServicio;

@RestController
@RequestMapping("/api/")
public class ComentarioController {
	
	@Autowired
	private ComentatarioServicio comentatarioServicio;
	
	@GetMapping("/publicaciones/{publicacionId}/comentarios")
	public List<ComentarioDTO> listarComentariosPorPublicacion(
			@PathVariable(value = "publicacionId") long publicacionId){
		return comentatarioServicio.obtenerComentarionPorPublicacion(publicacionId);
	}
	
	@GetMapping("/publicaciones/{publicacionId}/comentarios/{id}")
	public ResponseEntity<ComentarioDTO> obtenerComentarioPorId(
			@PathVariable(value = "publicacionId") long publicacionId,
			@PathVariable(value = "id") long comentarioId){
		ComentarioDTO comentarioDTO = comentatarioServicio.obtenerComentarioPorId(publicacionId, comentarioId);
		
		return new ResponseEntity<>(comentarioDTO, HttpStatus.OK); 
		
	}
	
	@PostMapping("/publicaciones/{publicacionId}/comentarios")
	public ResponseEntity<ComentarioDTO> guardarComentario(
			@PathVariable(value = "publicacionId") long publicacionId,
			@Valid @RequestBody ComentarioDTO comentarioDTO){
		
		return new ResponseEntity<>(comentatarioServicio.crearComentario(publicacionId, comentarioDTO),HttpStatus.CREATED);
	}
	
	@PutMapping("/publicaciones/{publicacionId}/comentarios/{id}")
	public ResponseEntity<ComentarioDTO> actualizarComentario(
			@PathVariable(value = "publicacionId") long publicacionId,
			@PathVariable(value = "id") long comentarioId,
			@Valid @RequestBody ComentarioDTO comentarioDTO){
		
		ComentarioDTO comentarioActualizado = comentatarioServicio.actializarComentario(publicacionId, comentarioDTO, comentarioId);
		
		return new ResponseEntity<>(comentarioActualizado, HttpStatus.OK);
	}
	
	@DeleteMapping("/publicaciones/{publicacionId}/comentarios/{id}")
	public ResponseEntity<String> eliminarComentario(
			@PathVariable(value = "publicacionId") long publicacionId,
			@PathVariable(value = "id") long comentarioId){
		
		comentatarioServicio.eliminarComentario(publicacionId, comentarioId);
			
		return new ResponseEntity<>("Comentario eliminado con exito", HttpStatus.OK);
	}
	
	
	
}








