package com.akkodis.codingchallenge.taco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class ItemDTO {
    private String tacoName;
    private List<String> toppings;
    private Double price;

    // Getters, Setters, Constructors
}
