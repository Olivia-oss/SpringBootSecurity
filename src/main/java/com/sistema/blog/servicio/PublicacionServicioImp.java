package com.sistema.blog.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;
import com.sistema.blog.entidades.Publicacion;
import com.sistema.blog.excepciones.ResouceNotFoundException;
import com.sistema.blog.repositorio.PublicacionRepositorio;

@Service
public class PublicacionServicioImp implements PublicacionServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PublicacionRepositorio publicacionRepositorio;

	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		// Convertimos de DTO a entidad;
		Publicacion publicacion = mapeareEntidad(publicacionDTO);

		Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);

		// Convertimos de entidad a DTO
		PublicacionDTO publicacionRespuesta = mapearDTO(nuevaPublicacion);

		return publicacionRespuesta;
	}

	@Override
	public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor,
			String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
				: Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

		Page<Publicacion> publicacones = publicacionRepositorio.findAll(pageable);

		List<Publicacion> listaDePublicaciones = publicacones.getContent();
		List<PublicacionDTO> contenido = listaDePublicaciones.stream().map(publicacon -> mapearDTO(publicacon))
				.collect(Collectors.toList());

		PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
		publicacionRespuesta.setContenido(contenido);
		publicacionRespuesta.setNumeroPagina(numeroDePagina);
		publicacionRespuesta.setMedidaPagina(medidaDePagina);
		publicacionRespuesta.setTotalElementos(publicacones.getTotalElements());
		publicacionRespuesta.setTotalPaginas(publicacones.getTotalPages());
		publicacionRespuesta.setUltima(publicacones.isLast());

		return publicacionRespuesta;
	}

	@Override
	public PublicacionDTO obtenerPublicacionPorId(long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Publicacion", "id", id));
		return mapearDTO(publicacion);
	}

	@Override
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Publicacion", "id", id));

		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());

		Publicacion publicacionActualizada = publicacionRepositorio.save(publicacion);

		return mapearDTO(publicacionActualizada);
	}

	@Override
	public void eliminarPublicacion(long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Publicacion", "id", id));

		publicacionRepositorio.delete(publicacion);

	}

	// Converte de entidad a DTO
	private PublicacionDTO mapearDTO(Publicacion publicacion) {
		PublicacionDTO publicacionDTO = modelMapper.map(publicacion, PublicacionDTO.class);
		return publicacionDTO;
	}

	// Converte de DTO a Entidad
	private Publicacion mapeareEntidad(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = modelMapper.map(publicacionDTO, Publicacion.class);
		return publicacion;
	}

}
