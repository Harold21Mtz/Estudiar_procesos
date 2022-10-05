package com.estudiarprocesos.harold.controllers;

import com.estudiarprocesos.harold.models.Product;
import com.estudiarprocesos.harold.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value= "/product/{id}")
    public ResponseEntity getProduct(@PathVariable Long id){
        Optional<Product> product= productRepository.findById(id);
        if(product.isPresent()){
            return new ResponseEntity(product, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value= "/product")
    public ResponseEntity listProduct(){
        List<Product> products= productRepository.findAll();
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @GetMapping(value= "/product/nombre/{nombre}")
    public ResponseEntity ListbyName(@PathVariable String nombre){
        List<Product> products= productRepository.findAllByNombre(nombre);
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @GetMapping(value= "/product/{nombre}/{categoria}")
    public ResponseEntity ListbyNameandCategory(@PathVariable String nombre,
                                                @PathVariable String categoria){
        List<Product> products= productRepository.findByNombreAndCategoria(nombre,categoria);
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(products, HttpStatus.OK);
    }
/*
    @GetMapping(value= "/product/precio/{precio}")
    public ResponseEntity ListbyPrice(@PathVariable double precio){
        List<Product> products= productRepository.findAllByPrecio(precio);
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(products, HttpStatus.OK);
    }
*/
    @PostMapping(value= "/product")
    public ResponseEntity createProduct(@RequestBody Product product){
        try{
            productRepository.save(product);
            return new ResponseEntity(product, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping(value="/product")
    public ResponseEntity updateProduct(@PathVariable Long id,@RequestBody Product product){
        Optional<Product> productBD = productRepository.findById(id);
        if(productBD.isPresent()){
            try{
                productBD.get().setNombre((product.getNombre()));
                productBD.get().setDescripcion((product.getDescripcion()));
                productBD.get().setCategoria((product.getCategoria()));
                productBD.get().setEmpresa_fabricante((product.getEmpresa_fabricante()));
                productBD.get().setCantidad((product.getCantidad()));
                productBD.get().setPrecio((product.getPrecio()));
                productRepository.save(productBD.get());
                return new ResponseEntity(productBD, HttpStatus.OK);

            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }

        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(value="/product/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        Optional<Product> productBD = productRepository.findById(id);
        if(productBD.isPresent()){
            productRepository.delete(productBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
