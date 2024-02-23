package com.arquisoft.reactiveprogramming.services;


import com.arquisoft.reactiveprogramming.dtos.ProductCreationDTO;
import com.arquisoft.reactiveprogramming.dtos.ProductRequestDto;
import com.arquisoft.reactiveprogramming.repositories.IProductDao;
import com.arquisoft.reactiveprogramming.services.interfaces.IProductService;
import com.arquisoft.reactiveprogramming.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
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

    @Override
    public Flux<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Mono<Product> updateProduct(String productId, Mono<ProductRequestDto> productRequestDtoMono) {
        return productDao.findById(productId)
                .flatMap(existingProduct -> productRequestDtoMono
                        .map(productRequestDto -> {
                            existingProduct.setName(productRequestDto.getName());
                            existingProduct.setEan(productRequestDto.getEan());
                            existingProduct.setCost(productRequestDto.getCost());
                            existingProduct.setPrice(productRequestDto.getPrice());
                            return existingProduct;
                        }))
                .flatMap(productDao::save);
    }
}
