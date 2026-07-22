package com.HugoVentrice.GestorDeLogistica.Security;

import com.HugoVentrice.GestorDeLogistica.service.CustomUserDetailsService;
import com.HugoVentrice.GestorDeLogistica.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    /*
       Para hablar entre la BD y SpringSecurity usamos SI o SI UserDetailsService, NUNCA usamos UsuarioService 'a pelo'.
       Esto ya que, usas UserDetails para definir para SpringSecurity que es de tu usuario su USERNAME/IDENTIFICADOR y su CONTRASEÑA.
     */

    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        final String correo = jwtService.extractUsername(jwt);

        if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(correo);

            if (jwtService.isValid(jwt, userDetails)) {
                /*
                    UsernamePasswordAuthenticationToken es una clase que se extiende de Authentication, y la usamos para poder pasársela a SecurityContextHolder.
                    Ya que SecurityContextHolder, solo acepta objetos Authentication (o sus herederos).
                 */
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}