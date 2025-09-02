package com.bank.bankapi;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthFilter extends OncePerRequestFilter {

	private static final String AUTH_TOKEN = "12345ABC"; // Token para la prueba

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");

		// Validar cabecera
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Falta token de autorización");
			return;
		}

		String token = authHeader.substring(7); // quitar "Bearer "

		if (!AUTH_TOKEN.equals(token)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido"); // No he logrado mostrar los
																						// mensajes personalizados de
																						// error
			return;
		}

		// Si todo bien seguir con la request
		filterChain.doFilter(request, response);
	}
}
