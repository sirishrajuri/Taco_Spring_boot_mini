package com.akkodis.codingchallenge.taco.repository;

import com.akkodis.codingchallenge.taco.entity.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends JpaRepository<Taco,Long> {

}
