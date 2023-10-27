package com.akkodis.codingchallenge.taco.repository;

import com.akkodis.codingchallenge.taco.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
