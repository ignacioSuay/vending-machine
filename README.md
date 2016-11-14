# The Vending Machine

## 1. Introduction

This project is an implementation of a vending machine

## 2. Technology stack

This application uses Java 8, Maven, Spring (Spring Boot) and logback.

## 3. Implementation code

Both part 1 and part 2 are implemented in the com.ignaciosuay.service.VendingMachineServiceImpl class which implements the VendingMachineService. 
This class will use the InventoryService to load/write the inventory from the coin-inventory.properties file.

## 4. Build the application

In order to build the project, please use the "install" Maven lifecycle as follows:

mvn clean install

The following command will create an executable jar file in the target folder. 

## 5. Run tests

This application contains 10 tests split between 3 different classes: 
 - CoinTest: Includes tests for the Coin enumeration.
 - InventoryServiceImplTest: Includes test cases scenarios which perform read and write operations over the inventory file.
 - VendingMachineServiceImplTest: Includes test cases scenarios which simulate the behavior of the vending machine. 

In order to run the test try:

mvn clean test
