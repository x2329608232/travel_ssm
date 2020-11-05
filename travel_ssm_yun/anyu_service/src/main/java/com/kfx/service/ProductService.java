package com.kfx.service;

import com.kfx.pojo.Product;

import java.util.List;

public interface ProductService {
    List<Product> finAll();

    void save(Product product);
}
