package com.example.reactiveProgramming.services.interfaces;


import com.example.reactiveProgramming.dtos.ProductCreationDTO;
import com.example.reactiveProgramming.entities.Product;
import reactor.core.publisher.Mono;

public interface IProductService {

    Mono<ProductCreationDTO> createProduct(Product product);

    Mono<Void> deleteProduct(String productId);
}
