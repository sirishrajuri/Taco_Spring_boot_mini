package com.akkodis.codingchallenge.taco.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tacoId;

    private String tacoName;
    private Integer tacoCost;
    private Integer numberAvailable;

}
