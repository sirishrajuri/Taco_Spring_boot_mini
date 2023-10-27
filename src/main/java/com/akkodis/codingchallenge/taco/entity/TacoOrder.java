package com.akkodis.codingchallenge.taco.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TacoOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tacoOrderId;

    @ManyToOne
    private Taco taco;

    @ManyToOne
    private Orders order;

    @OneToMany(mappedBy = "tacoOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TacoOrderIngredient> tacoOrderIngredients = new ArrayList<>();

    private Integer tacoQuantity; // This represents the number of such tacos in the order

    // to add TacoOrderIngredient to the TacoOrder
    public void addTacoOrderIngredient(TacoOrderIngredient tacoOrderIngredient) {
        tacoOrderIngredients.add(tacoOrderIngredient);
        tacoOrderIngredient.setTacoOrder(this);
    }
}

