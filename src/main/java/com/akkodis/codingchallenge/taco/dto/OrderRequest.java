package com.akkodis.codingchallenge.taco.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private List<TacoOrderRequest> tacoOrders;
}
