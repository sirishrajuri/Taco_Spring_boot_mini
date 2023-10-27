package com.akkodis.codingchallenge.taco.services;

import com.akkodis.codingchallenge.taco.entity.Taco;
import com.akkodis.codingchallenge.taco.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TacoService {

    @Autowired
    private TacoRepository tacoRepository;

    public void deductTacoQuantity(Long tacoId, Integer quantityToDeduct) {
        Taco taco = tacoRepository.findById(tacoId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Taco ID"));

        if (taco.getNumberAvailable() < quantityToDeduct) {
            throw new IllegalArgumentException("Insufficient quantity for taco: " + taco.getTacoName());
        }

        taco.setNumberAvailable(taco.getNumberAvailable() - quantityToDeduct);
        tacoRepository.save(taco);
    }

    public Taco getTaco(Long tacoId){
        return tacoRepository.findById(tacoId).orElse(null);
    }
}
