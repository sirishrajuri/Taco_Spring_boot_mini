Postman Requests:

1. Get All Tacos:

Method: GET 
http://localhost:8080/allTacos

2. Get All Ingredients:

Method: GET
http://localhost:8080/allIngredients

3. Place a Order:

Method: POST
http://localhost:8080/order/placeOrder

Sample Request Body:

   {
   "tacoOrders": [
   {
   "tacoId": 1,
   "ingredients": [
   {
   "ingredientId": 1,
   "quantity": 2
   },
   {
   "ingredientId": 2,
   "quantity": 1
   }
   ],
   "tacoQuantity": 3
   },
   {
   "tacoId": 2,
   "ingredients": [
   {
   "ingredientId": 1,
   "quantity": 1
   }
   ],
   "tacoQuantity": 1
   }
   ]
   }

4. Mark Order As Ready

Method: POST
http://localhost:8080/orders/ready/{orderId}

Replace {orderId} with the order id.

5. Get Bill For Order

Method: GET
http://localhost:8080/orders/bill/{orderId}

Replace {orderId} with the order id.