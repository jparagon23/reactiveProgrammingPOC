package com.arquisoft.reactiveprogramming.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("products")
public class Product {
    @Id
    private String id;
    private String name;
    private String ean;
    private String cost;
    private String price;

    public Product(String name, String ean, String cost, String price) {
        this.name = name;
        this.ean = ean;
        this.cost = cost;
        this.price = price;
    }
}
