package com.arquisoft.reactiveprogramming.controllers;

import com.arquisoft.reactiveprogramming.dtos.ProductCreationDTO;
import com.arquisoft.reactiveprogramming.dtos.ProductRequestDto;
import com.arquisoft.reactiveprogramming.entities.Product;
import com.arquisoft.reactiveprogramming.services.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    @PostMapping
    public Mono<ProductCreationDTO> createProduct(@RequestBody Mono<ProductRequestDto> productRequestDtoMono) {
        return productRequestDtoMono
                .map(this::convertToProduct)
                .flatMap(productService::createProduct)
                .map(productCreationResult -> new ProductCreationDTO(productCreationResult.getUserId()))
                .onErrorResume(Exception.class, this::handleException);
    }

    @DeleteMapping("/{productId}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String productId) {
        return productService.deleteProduct(productId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }


    @GetMapping()
    public Flux<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @PutMapping("/{productId}")
    public Mono<ResponseEntity<Product>> updateProduct(
            @PathVariable String productId,
            @RequestBody Mono<ProductRequestDto> productRequestDtoMono) {
        return productService.updateProduct(productId, productRequestDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    private Product convertToProduct(ProductRequestDto productRequestDto) {
        return new Product(
                productRequestDto.getName(),
                productRequestDto.getEan(),
                productRequestDto.getCost(),
                productRequestDto.getPrice()
        );
    }

    private Mono<ProductCreationDTO> handleException(Throwable ex) {
        return Mono.error(ex);
    }

}