package com.peeyush.controller;

import com.peeyush.model.Stock;
import com.peeyush.service.StockService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class StockController {

    @Autowired
    private StockService stockService;

    // List All Stocks
    @RequestMapping(value = "stocks", method = RequestMethod.GET)
    public ResponseEntity<List<Stock>> getAllStocks() {
      return stockService.getAllStocksResponse();
    }


}
