package com.HugoVentrice.GestorDeLogistica.repository;

import com.HugoVentrice.GestorDeLogistica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
