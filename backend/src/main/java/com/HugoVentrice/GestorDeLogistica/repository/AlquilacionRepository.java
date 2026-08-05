package com.HugoVentrice.GestorDeLogistica.repository;

import com.HugoVentrice.GestorDeLogistica.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlquilacionRepository extends JpaRepository<Alquilacion, Long> {
    List<Alquilacion> findByProducto(Producto producto);
    List<Alquilacion> findByUsuario(Usuario usuario);
    List<Alquilacion> findByPersonal(Personal personal);
}