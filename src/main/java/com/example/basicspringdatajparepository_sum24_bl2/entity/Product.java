package com.example.basicspringdatajparepository_sum24_bl2.entity;


import jakarta.persistence.*;
import lombok.*;

@NamedQuery(
        name = "Product.findByNameV4",
        query = "SELECT p FROM Product p WHERE p.name = ?1"
)
@NamedNativeQuery(
        name = "Product.findByNameV5",
        query = "SELECT * FROM product p WHERE p.name = ?1",
        resultClass = Product.class
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
