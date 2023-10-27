package com.akkodis.codingchallenge.taco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private List<ItemDTO> items;
    private Double totalWithoutTax;
    private Double stateTax;
    private Double federalTax;
    private Double totalWithTax;

    // Getters, Setters, Constructors
}

