package com.akkodis.codingchallenge.taco;

import com.akkodis.codingchallenge.taco.controller.TacoManagementController;
import com.akkodis.codingchallenge.taco.dto.OrderRequest;
import com.akkodis.codingchallenge.taco.dto.BillDTO;
import com.akkodis.codingchallenge.taco.entity.Ingredient;
import com.akkodis.codingchallenge.taco.entity.Orders;
import com.akkodis.codingchallenge.taco.entity.Taco;
import com.akkodis.codingchallenge.taco.repository.IngredientRepository;
import com.akkodis.codingchallenge.taco.repository.TacoRepository;
import com.akkodis.codingchallenge.taco.services.OrderService;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Data
@SpringBootTest
public class TacoManagementControllerTest {

    @InjectMocks
    private TacoManagementController tacoManagementController;

    @MockBean
    private OrderService orderService;

    @Mock
    private Taco taco;

    @Mock
    private Ingredient ingredient;

    @Mock
    private TacoRepository tacoRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private Orders orders;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // If not present already, it initializes the mocks
        tacoManagementController = new TacoManagementController();

        tacoManagementController.setTacoRepository(tacoRepository);
        tacoManagementController.setIngredientRepository(ingredientRepository);
        tacoManagementController.setOrderService(orderService);
    }


    @Test
    public void testFindAllTacos() {
        when(tacoManagementController.findAllTacos()).thenReturn(Arrays.asList(taco));
        assertEquals(1, tacoManagementController.findAllTacos().size());
    }

    @Test
    public void testFindAllAdditionalToppings() {
        when(tacoManagementController.findAllAdditionalToppings()).thenReturn(Arrays.asList(ingredient));
        assertEquals(1, tacoManagementController.findAllAdditionalToppings().size());
    }

    @Test
    public void testPlaceOrder() {
        OrderRequest orderRequest = new OrderRequest();
        when(orderService.placeOrder(orderRequest)).thenReturn(1L);
        assertEquals(1L, tacoManagementController.placeOrder(orderRequest));
    }

    @Test
    public void testMarkOrderAsReady() {
        Long orderId = 1L;

        doNothing().when(orderService).markOrderAsReady(orderId);

        ResponseEntity<String> response = tacoManagementController.markOrderAsReady(orderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order marked as ready!", response.getBody());
    }

    @Test
    public void testGetBillForOrder_WhenOrderNotPresent() {
        Long orderId = 1L;
        when(orderService.findOrderById(orderId)).thenReturn(null);

        ResponseEntity<Object> response = tacoManagementController.getBillForOrder(orderId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Order Id not present", response.getBody());
    }

    @Test
    public void testGetBillForOrder_OrderNotReady() {
        Long orderId = 1L;
        when(orders.getReady()).thenReturn(false);
        when(orderService.findOrderById(orderId)).thenReturn(orders);

        ResponseEntity<Object> response = tacoManagementController.getBillForOrder(orderId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Order is not ready", response.getBody());
    }

    @Test
    public void testGetBillForOrder_Successful() {
        Long orderId = 1L;
        BillDTO mockBill = new BillDTO();
        when(orders.getReady()).thenReturn(true);
        when(orderService.findOrderById(orderId)).thenReturn(orders);
        when(orderService.provideTheBill(orderId)).thenReturn(mockBill);

        ResponseEntity<Object> response = tacoManagementController.getBillForOrder(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBill, response.getBody());
    }

    @Test
    public void testGetBillForOrder_OrderNotFoundException() {
        Long orderId = 1L;
        when(orderService.findOrderById(orderId)).thenReturn(null);
        when(orderService.provideTheBill(orderId)).thenThrow(new IllegalArgumentException("Order not found!"));

        ResponseEntity<Object> response = tacoManagementController.getBillForOrder(orderId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Order Id not present", response.getBody());
    }

    @Test
    public void testGetBillForOrder_OrderNotReadyException() {
        Long orderId = 1L;
        when(orders.getReady()).thenReturn(false);
        when(orderService.findOrderById(orderId)).thenReturn(orders);
        when(orderService.provideTheBill(orderId)).thenThrow(new IllegalArgumentException("Order is not ready!"));

        ResponseEntity<Object> response = tacoManagementController.getBillForOrder(orderId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Order is not ready", response.getBody());
    }


}
