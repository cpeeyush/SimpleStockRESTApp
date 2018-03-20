package com.peeyush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Class for our StockApplication.
 * it boots the Springboot.
 */
@SpringBootApplication
public class StockRestApp
{
    public static void main( String[] args )
    {

      SpringApplication.run(StockRestApp.class, args);
    }
}
