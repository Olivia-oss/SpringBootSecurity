package com.sistema.blog.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.entidades.Comentario;
import com.sistema.blog.entidades.Publicacion;
import com.sistema.blog.excepciones.BlogAppException;
import com.sistema.blog.excepciones.ResouceNotFoundException;
import com.sistema.blog.repositorio.ComentarioRepositorio;
import com.sistema.blog.repositorio.PublicacionRepositorio;

@Service
public class ComentarioServicioImp implements ComentatarioServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ComentarioRepositorio comentarioRepositorio;

	@Autowired
	private PublicacionRepositorio publicacionRepositorio;

	@Override
	public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO) {

		Comentario comentario = mapearEntidad(comentarioDTO);
		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(() -> new ResouceNotFoundException("Comentario", "id", publicacionId));
		comentario.setPublicacion(publicacion);
		Comentario nuevoComentaio = comentarioRepositorio.save(comentario);

		return mapearDTO(nuevoComentaio);
	}

	@Override
	public List<ComentarioDTO> obtenerComentarionPorPublicacion(long publicacionId) {
		List<Comentario> comentarios = comentarioRepositorio.findByPublicacionId(publicacionId);
		return comentarios.stream().map(comentario -> mapearDTO(comentario)).collect(Collectors.toList());
	}

	@Override
	public ComentarioDTO obtenerComentarioPorId(long publicacionId, long comentarioId) {

		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(() -> new ResouceNotFoundException("Comentario", "id", publicacionId));

		Comentario comentario = comentarioRepositorio.findById(comentarioId)
				.orElseThrow(() -> new ResouceNotFoundException("Comentario", "id", comentarioId));

		if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
		}

		return mapearDTO(comentario);
	}

	@Override
	public ComentarioDTO actializarComentario(long publicacionId, ComentarioDTO solicitudDeComentario,
			long comentarioId) {

		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(() -> new ResouceNotFoundException("Comentario", "id", publicacionId));

		Comentario comentario = comentarioRepositorio.findById(comentarioId)
				.orElseThrow(() -> new ResouceNotFoundException("Comentario", "id", comentarioId));

		if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
		}

		comentario.setNombre(solicitudDeComentario.getNombre());
		comentario.setEmail(solicitudDeComentario.getEmail());
		comentario.setCuerpo(solicitudDeComentario.getCuerpo());

		Comentario comentarioActulizado = comentarioRepositorio.save(comentario);

		return mapearDTO(comentarioActulizado);
	}

	@Override
	public void eliminarComentario(long publicacionId, long comentarioId) {
		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(() -> new ResouceNotFoundException("Comentario", "id", publicacionId));

		Comentario comentario = comentarioRepositorio.findById(comentarioId)
				.orElseThrow(() -> new ResouceNotFoundException("Comentario", "id", comentarioId));

		if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
		}

		comentarioRepositorio.delete(comentario);

	}

	private ComentarioDTO mapearDTO(Comentario comentario) {
		ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);
		return comentarioDTO;
	}

	private Comentario mapearEntidad(ComentarioDTO comentarioDTO) {
		Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
		return comentario;
	}

}


