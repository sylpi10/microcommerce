package com.ecommerce.microcommerce.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.exceptions.UnfoundProductException;
import com.ecommerce.microcommerce.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api
@RestController
public class ProductController {

    @Autowired
     private ProductDao productDao;

    @GetMapping(value = "Products")
    public List<Product> allProducts(){
        return productDao.findAll();
    }

    //Products/{id}
    @ApiOperation(value = "get product with id")
    @GetMapping(value="Products/{id}")
    public Product showProduct(@PathVariable int id) throws UnfoundProductException {
        Product prod = productDao.findById(id);
        if (prod == null) throw new UnfoundProductException("Product with id " + id +" doesn't exist");

        return prod;
    }

    @PostMapping(value="/Products")
    public ResponseEntity<Void> addProduct(@Valid @RequestBody Product prod){
        Product product = productDao.save(prod);
        if (prod == null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "test/products/{limitPrice}")
        public List<Product> byPrice(@PathVariable int limitPrice) {
        return productDao.seekWithPriceLimit(limitPrice);
    }
}
