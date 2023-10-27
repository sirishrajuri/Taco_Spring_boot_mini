package com.akkodis.codingchallenge.taco.dto;

import lombok.Data;

import java.util.List;

@Data
public class TacoOrderRequest {
    private Long tacoId;
    private List<TacoOrderIngredientRequest> ingredients; // List of ingredients with quantities
    private Integer tacoQuantity;
}
