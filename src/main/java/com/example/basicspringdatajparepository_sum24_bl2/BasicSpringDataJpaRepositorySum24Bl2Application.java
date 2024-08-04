package com.example.basicspringdatajparepository_sum24_bl2;

import com.example.basicspringdatajparepository_sum24_bl2.entity.Product;
import com.example.basicspringdatajparepository_sum24_bl2.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

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

        // 5. Pagination
        int pageNo = 0; // trang 0, 1, ...
        int pageSize = 2; // so phan tu cua trang

        // creating pageable obj
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        // get all info via Page
        Page<Product> page = productRepository.findAll(pageable);
        List<Product> products = page.getContent();
        products.forEach(p -> logger.info(p.toString()));

        //total pages
        int totalPages = page.getTotalPages();
        logger.info("Total Pages: " + totalPages);

        // total elements
        long totalElements = page.getTotalElements();
        logger.info("Number of elements: " + totalElements);

        // size
        int size = page.getSize();
        logger.info("Size of elements: " + size);

        // last, fisrt
        boolean isLast = page.isLast();
        boolean isFirst = page.isFirst();

        logger.info("Is last page: " + isLast);
        logger.info("Is first page: " + isFirst);

        // 6. Sorting (sap xep)
        String sortBy = "name";
        String sortDir = "desc"; // default = "asc"

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy)
                : Sort.by(sortBy).descending();

        List<Product> products1 = productRepository.findAll(sort);
        products1.forEach(p -> logger.info(p.toString()));

        // sorting with multiple fields
        //Sort.by("name").and(Sort.by("price").descending());

        //7. Pagination + Sorting
        Page<Product> products2 = productRepository
                .findAll(PageRequest.of(pageNo, pageSize, sort));
        products2.forEach(p -> logger.info(p.toString()));


    }
}

