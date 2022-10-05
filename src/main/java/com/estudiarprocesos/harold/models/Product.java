package com.estudiarprocesos.harold.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "productos")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 100, nullable = false)
    private String nombre;
    @Column(length = 500, nullable = false)
    private String descripcion;
    @Column(length = 200, nullable = false)
    private String categoria;
    @Column(length = 200, nullable = false)
    private String empresa_fabricante;
    @Column(length = 1000, nullable = false)
    private int cantidad;
    @Column(length = 200, nullable = false)
    private double precio;

}
