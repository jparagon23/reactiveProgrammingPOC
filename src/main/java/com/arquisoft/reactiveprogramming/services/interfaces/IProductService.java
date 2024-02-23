package com.arquisoft.reactiveprogramming.services.interfaces;


import com.arquisoft.reactiveprogramming.dtos.ProductCreationDTO;
import com.arquisoft.reactiveprogramming.dtos.ProductRequestDto;
import com.arquisoft.reactiveprogramming.entities.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Mono<ProductCreationDTO> createProduct(Product product);

    Mono<Void> deleteProduct(String productId);

    Flux<Product> getAllProducts();

    Mono<Product> updateProduct(String productId, Mono<ProductRequestDto> productRequestDtoMono);
}
