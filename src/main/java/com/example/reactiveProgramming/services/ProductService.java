package com.example.reactiveProgramming.services;


import com.example.reactiveProgramming.dtos.ProductCreationDTO;
import com.example.reactiveProgramming.entities.Product;
import com.example.reactiveProgramming.repositories.IProductDao;
import com.example.reactiveProgramming.services.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ProductService implements IProductService {

    private final IProductDao productDao;

    @Override
    public Mono<ProductCreationDTO> createProduct(Product product) {
        // Save the product to the database using the productRepository
        return productDao.save(product)
                .map(savedProduct -> new ProductCreationDTO(savedProduct.getId()));
    }

    @Override
    @Transactional
    public Mono<Void> deleteProduct(String productId) {
        return productDao.deleteById(productId);
    }
}
