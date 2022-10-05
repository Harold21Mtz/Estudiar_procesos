package com.estudiarprocesos.harold.repository;

import com.estudiarprocesos.harold.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNombre (String nombre);
    //List<Product> findAllByPrecio (double precio);
    List<Product> findByNombreAndCategoria (String nombre, String categoria);

}
