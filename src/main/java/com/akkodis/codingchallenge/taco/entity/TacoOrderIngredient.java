package com.akkodis.codingchallenge.taco.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TacoOrderIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TacoOrder tacoOrder;

    @ManyToOne
    private Ingredient ingredient;

    private Integer quantity;
}

