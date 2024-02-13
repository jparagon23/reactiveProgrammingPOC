package com.example.reactiveProgramming.dtos;

import lombok.Data;

@Data
public class ProductRequestDto {

    private String name;
    private String ean;
    private String cost;
    private String price;

}
