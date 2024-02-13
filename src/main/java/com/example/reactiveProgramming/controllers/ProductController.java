package com.example.reactiveProgramming.controllers;

import com.example.reactiveProgramming.dtos.ProductCreationDTO;
import com.example.reactiveProgramming.dtos.ProductRequestDto;
import com.example.reactiveProgramming.entities.Product;
import com.example.reactiveProgramming.services.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private  final IProductService productService;

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