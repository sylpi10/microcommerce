package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    Product findById(int id);
    List<Product> findByPriceGreaterThan(int price);

    @Query("SELECT id, name, price FROM Product p WHERE p.price > :limitPrice")

    List<Product> seekWithPriceLimit(@Param("limitPrice") int price);
}
