package com.example.basicspringdatajparepository_sum24_bl2;

import com.example.basicspringdatajparepository_sum24_bl2.entity.Product;
import com.example.basicspringdatajparepository_sum24_bl2.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicSpringDataJpaRepositorySum24Bl2Application implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(getClass());

    final
    ProductRepository productRepository;

    public BasicSpringDataJpaRepositorySum24Bl2Application(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BasicSpringDataJpaRepositorySum24Bl2Application.class, args);
        System.out.println("running...");
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. built-in methods: save, find, findAll, ...
        Product product = new Product();
        //product.setId(1L);
        product.setName("Product 1");
        product.setPrice(1);

        Product savedProduct = productRepository.save(product);

        productRepository.findAll().forEach(p -> logger.info(p.toString()));

        // 2. Query methods (finder methods)
        // read...By, get...By, query...By
        // distinct
        // AND, OR
        // containing

        // method tim cac sp co ten chua "Product 100"
        productRepository.findByNameContaining("Product 100")
                .forEach(p -> logger.info(p.getName()));

        // 3.
        productRepository.findByNameOrIdV2("Product 1", 1002L)
                .forEach(p -> logger.info(p.getName()));

        //4.
        productRepository.findByNameV5("Product 1")
                .forEach(p -> logger.info(p.getName()));
    }
}
