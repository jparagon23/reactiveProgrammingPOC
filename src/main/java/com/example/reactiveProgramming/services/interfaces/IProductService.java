package com.example.reactiveProgramming.services.interfaces;


import com.example.reactiveProgramming.dtos.ProductCreationDTO;
import com.example.reactiveProgramming.dtos.ProductRequestDto;
import com.example.reactiveProgramming.entities.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface IProductService {

    Mono<ProductCreationDTO> createProduct(Product product);

    Mono<Void> deleteProduct(String productId);

    Flux<Product> getAllProducts();

    Mono<Product> updateProduct(String productId, Mono<ProductRequestDto> productRequestDtoMono);
}
