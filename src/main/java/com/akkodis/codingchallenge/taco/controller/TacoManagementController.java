package com.akkodis.codingchallenge.taco.controller;

import com.akkodis.codingchallenge.taco.dto.BillDTO;
import com.akkodis.codingchallenge.taco.dto.OrderRequest;
import com.akkodis.codingchallenge.taco.entity.Ingredient;
import com.akkodis.codingchallenge.taco.entity.Orders;
import com.akkodis.codingchallenge.taco.repository.IngredientRepository;
import com.akkodis.codingchallenge.taco.repository.TacoRepository;
import com.akkodis.codingchallenge.taco.services.OrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.akkodis.codingchallenge.taco.entity.Taco;
import java.util.*;

@Data
@RequestMapping("/")
@RestController
public class TacoManagementController {

    @Autowired
    private TacoRepository tacoRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("/allTacos")
    public List<Taco> findAllTacos(){
        return tacoRepository.findAll();
    }

    @GetMapping("/allIngredients")
    public List<Ingredient> findAllAdditionalToppings(){
        return ingredientRepository.findAll();
    }

    @PostMapping("/order/placeOrder")
    public Long placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    @PostMapping("/ready/{orderId}")
    public ResponseEntity<String> markOrderAsReady(@PathVariable Long orderId) {
        orderService.markOrderAsReady(orderId);
        return ResponseEntity.ok("Order marked as ready!");
    }

    @GetMapping("/bill/{orderId}")
    public ResponseEntity<Object> getBillForOrder(@PathVariable Long orderId) {
        try {
            Orders orders = orderService.findOrderById(orderId);
            if (orders==null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Id not present");
            }
            if (!orders.getReady()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not ready");
            }
            BillDTO bill = orderService.provideTheBill(orderId);
            return ResponseEntity.ok(bill);
        } catch (Exception ex) {  // Assuming you throw this exception when order is not found.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }


}
