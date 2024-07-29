package com.example.basicspringdatajparepository_sum24_bl2.repository;

import com.example.basicspringdatajparepository_sum24_bl2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //1. save, ...

    //2.
    List<Product> findByNameContaining(String name);

    //3.
    @Query("SELECT p FROM Product p WHERE p.name=?1 OR p.id=?2")
    List<Product> findByNameOrId(String name, Long id);

    @Query(value = "SELECT * FROM product p WHERE p.name=:name OR p.id=:id", nativeQuery = true)
    List<Product> findByNameOrIdV2(String name, Long id);

    //4
    List<Product> findByNameV4(String name);

    @Query(nativeQuery = true)
    List<Product> findByNameV5(String name);
}
