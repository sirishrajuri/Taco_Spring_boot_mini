package com.akkodis.codingchallenge.taco.dto;

import lombok.Data;

@Data
public class TacoOrderIngredientRequest {
    private Long ingredientId;
    private Integer quantity;
}
