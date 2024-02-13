package com.example.reactiveProgramming.repositories;

import com.example.reactiveProgramming.entities.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IProductDao extends ReactiveMongoRepository<Product,String>{

}
