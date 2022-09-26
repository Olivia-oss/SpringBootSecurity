package com.sistema.blog.seguridad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.sistema.blog.excepciones.BlogAppException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	@Value("${app.jwt-secret}")
	private String jwtSecrect;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private String jwtExpirationInMs;
	
	
	public String generalToken(Authentication authentication) {	
		
		
			String username = authentication.getName();
			Date fechaActual = new Date();
			
			long secondExpiration = fechaActual.getTime() + Long.parseLong(jwtExpirationInMs);
			
			Calendar calendar =  Calendar.getInstance();   
			calendar.setTimeInMillis(secondExpiration);
			Date fechaExpiracion = calendar.getTime();

			String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion)
					.signWith(SignatureAlgorithm.HS512, jwtSecrect).compact();
		
			return token;
			
		
		
	}
	
	public String obtenerUserNameDelJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecrect).parseClaimsJws(token).getBody();
		
		return claims.getSubject();
	}
	
	
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecrect).parseClaimsJws(token);
			return true;
		}catch (SignatureException e) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Firma JWT no valida");
		}catch (MalformedJwtException e) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no valida");
		}catch (ExpiredJwtException e) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT caducado");
		}catch (UnsupportedJwtException e) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no compatible");
		}catch (IllegalArgumentException e) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "La cadena claims JWT esta vacia");
		}
	}
	
	
	
	
}







