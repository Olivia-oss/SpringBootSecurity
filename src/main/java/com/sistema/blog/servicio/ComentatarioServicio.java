package com.sistema.blog.servicio;

import java.util.List;

import com.sistema.blog.dto.ComentarioDTO;


public interface ComentatarioServicio {
	
	public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO);
	
	public List<ComentarioDTO> obtenerComentarionPorPublicacion(long publicacionId);
	
	public ComentarioDTO obtenerComentarioPorId(long publicacionId, long comentarioId);
	
	public ComentarioDTO actializarComentario(long publicacionId, ComentarioDTO solicitudDeComentario, long comentarioId);

	public void eliminarComentario(long publicacionId, long comentarioId);

}
