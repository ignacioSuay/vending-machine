package com.ignaciosuay;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VendingMachineApp {

    private static final Logger log = LoggerFactory.getLogger(VendingMachineApp.class);

    /**
     * Main method, used to run the application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        log.info("Running Vending Machine application");
        SpringApplication.run(VendingMachineApp.class, args);
    }

}
