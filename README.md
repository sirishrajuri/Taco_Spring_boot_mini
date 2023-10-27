# Java Challenge: Schwab Taco

The following is an overview for the Schwab taco challenge. It is assumed that the challenger has the experience to set up the system and write the test themselves.

## Of Note
The challenger may be asked to defend the code they wrote in an interview, should they be selected.

## Code Review
One of our engineers will review the submitted code. Adhering to best practices and principles is recommended as the challenger will be judged on the structure and cleanliness of the code. Additionally, all tests should run.

## Automated Testing

**Note that submissions without unit testing will not be reviewed!**

This project has Spring test, junit, and Mockito installed already. 

Integration tests are optional. Note that H2 is in the pom.xml should you choose to write integration tests.

## System Setup

In order to set up the challenge, you will need:

1. JDK 17 or greater
2. Maven 3 or greater
3. An IDE of your choosing

## Dependencies
Challengers can look at the pom.xml file to see the full list of dependencies. Additionally, challengers can add more dependencies to pom.xml if they like.

## CHALLENGER_NOTES.md
Any notes you would like for the reviewer to know, leave them in the CHALLENGER_NOTES.md file.

## Challenge
Note this a backend code only test. No UI is required.

You are writing an MVP for a Taco truck. For now, the truck needs to have the following ReST endpoints.

### findAllTacos
This service returns all the tacos available. The returned list of Tacos will include the following information:

    * Taco ID: Unique ID leveraged when ordering
    * Taco Name: What kind of taco is it
    * Taco Cost: Price for this Taco
    * Number Availalbe: Tacos left for sale

When considering your data, don't overthink it. Just give 2 or 3 examples of tacos.

Note that HATEOS has been installed in the dependencies. Leverging pagination is recommended, however, not required for this service. 

### findAllAdditionalTopings
Provides a list of additional Topings (salsa, guacamole, etc). The list should return:

    * Ingredient Id: Unique id for the ingredient
    * Ingredient Name: Name of the additional ingredient
    * Ingredient Cost: Price of the additional ingredient

When considering your data, don't overthink it. Just give 2 or 3 examples of additional Topings.

Note that HATEOS has been installed in the dependencies. Leverging pagination is recommended, however, not required for this service. 

### orderTacos
Allows for the ordering of a number of Tacos. The order may:

    * Have many tacos
    * Have additional toppings on a taco
    * You can order the same taco a number of times with different additional topings on each.

The return, from this action, is the orderId of this request. You will need that for the next couple of API calls.

### orderReady (bonus points)
Note that this is a bonus endpoint.

This service, providing the order id, is the only call from the kitchen. It lets the system know that an order has been delivered and is ready for pick up.

Don't forget to decrement the number of tacos available for the **findAllTacos** endpoint.

### provideTheBill
This service will, given the order id, calculate the bill for an order. Note that this bill, when returned will need the following data:

    * Itemized list of tacos with their additional topings. Each item has their price. You can bundle them up as well if you have the same tacos.
    * Total without tax
    * Calculated state tax ( Bonus points for making it configurable )
    * Calculated federal tax ( Bonus points for making it configurable )
    * Total with tax

## Some Additional Notes

It is **NOT** important that you finish all the endpoints in this challenge. It is better that you write good clean code as well as unit tests for all the code. 

Additionally, so its a little easier, you only need to write unit tests... no need to leverage the spring launcher for tests. With this in mind, you will not need to write the bootstrapping of data.

In short, the reviewer will only run the tests and read the code. The application itself will not be run.

Finally, there is **NO** need to write tests for Pojos or Repository code. You would need intergration tests for that. The reasoning is that:

1. This is not supposed to take you forever to compete
2. We have SpringJPA in the pom so you only would need interfaces.... so no need.

## Submitting
When submitting, please just zip up the folder (without the `target` folder) and send it to your recruiter.

Make sure to include any notes that you would like the reviewer to see in the `CHALLENGER_NOTES.md` file.
