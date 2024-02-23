package com.arquisoft.reactiveprogramming.repositories;

import com.arquisoft.reactiveprogramming.entities.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IProductDao extends ReactiveMongoRepository<Product, String> {

}
