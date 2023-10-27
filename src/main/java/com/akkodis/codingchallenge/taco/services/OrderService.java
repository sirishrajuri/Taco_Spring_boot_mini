package com.akkodis.codingchallenge.taco.services;

import com.akkodis.codingchallenge.taco.dto.*;
import com.akkodis.codingchallenge.taco.entity.*;
import com.akkodis.codingchallenge.taco.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TacoService tacoService;

    @Autowired
    private IngredientService ingredientService;

    @Value("${state.tax.rate}")
    private Double stateTaxRate;

    @Value("${federal.tax.rate}")
    private Double federalTaxRate;

    public Long placeOrder(OrderRequest orderRequest) {
        Orders order = new Orders();
        List<TacoOrder> tacoOrders = new ArrayList<>();

        for (TacoOrderRequest tor : orderRequest.getTacoOrders()) {
            Taco taco = tacoService.getTaco(tor.getTacoId());  // Using a service method to fetch Taco

            if (taco.getNumberAvailable() < tor.getTacoQuantity()) {
                throw new IllegalArgumentException("Requested quantity for taco " + taco.getTacoName() + " is not available. Only " + taco.getNumberAvailable() + " left.");
            }

            tacoService.deductTacoQuantity(tor.getTacoId(), tor.getTacoQuantity());

            TacoOrder tacoOrder = new TacoOrder();
            tacoOrder.setTaco(taco);
            tacoOrder.setTacoQuantity(tor.getTacoQuantity());
            tacoOrder.setOrder(order);

            List<TacoOrderIngredient> ingredients = new ArrayList<>();
            for (TacoOrderIngredientRequest ir : tor.getIngredients()) {
                Ingredient ingredient = ingredientService.getIngredient(ir.getIngredientId());  // Using a service method to fetch Ingredient

                ingredientService.deductIngredientQuantity(ir.getIngredientId(), ir.getQuantity());

                TacoOrderIngredient toi = new TacoOrderIngredient();
                toi.setIngredient(ingredient);
                toi.setQuantity(ir.getQuantity());
                toi.setTacoOrder(tacoOrder);

                ingredients.add(toi);
            }
            tacoOrder.setTacoOrderIngredients(ingredients);
            tacoOrders.add(tacoOrder);
        }

        order.setTacoOrders(tacoOrders);
        orderRepository.save(order);
        return order.getOrderId();
    }

    public void markOrderAsReady(Long orderId) {
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found!"));
        orders.setReady(true);
        orderRepository.save(orders);
    }

    public BillDTO provideTheBill(Long orderId) {
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found!"));

        List<ItemDTO> items = new ArrayList<>();
        Double totalWithoutTax = 0.0;
        // Double x;

        for (TacoOrder tacoOrder : orders.getTacoOrders()) {
            Double tacoPrice = Double.valueOf(tacoOrder.getTaco().getTacoCost() * tacoOrder.getTacoQuantity());
            List<String> toppings = tacoOrder.getTacoOrderIngredients().stream()
                    .map(TacoOrderIngredient -> TacoOrderIngredient.getIngredient().getIngredientName())
                    .collect(Collectors.toList());
              Double toppingsCost = tacoOrder.getTacoOrderIngredients().stream()
                    .mapToDouble(TacoOrderIngredient -> TacoOrderIngredient.getIngredient().getIngredientCost())
                    .sum();

               items.add(new ItemDTO(tacoOrder.getTaco().getTacoName()+"("+tacoOrder.getTacoQuantity()+")", toppings, tacoPrice + toppingsCost));
            totalWithoutTax += tacoPrice + toppingsCost;
        }

        Double stateTax = totalWithoutTax * stateTaxRate;
        Double federalTax = totalWithoutTax * federalTaxRate;
        Double totalWithTax = Math.round((totalWithoutTax + stateTax + federalTax)*100.0)/100.0;


        return new BillDTO(items, totalWithoutTax, stateTax, federalTax, totalWithTax);
    }


    public Orders findOrderById(Long orderId){
        return orderRepository.findById(orderId).orElse(null);
    }
}


