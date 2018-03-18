package com.peeyush.controller;

import com.peeyush.helper.SimpleInMemoryNumericIdGenerator;
import com.peeyush.model.Stock;
import com.peeyush.request.CreateStockRequest;
import com.peeyush.service.StockService;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    // List One Stock
    @RequestMapping(value = "stocks/{id}", method = RequestMethod.GET)
    public ResponseEntity<Stock> getSingleStock(@PathVariable Long id) {
      return stockService.getSingleStockResponse(id);
    }

    // Create New Stock
    @RequestMapping(value = "stocks", method = RequestMethod.POST)
    public ResponseEntity<Stock> createNewStock(@RequestBody CreateStockRequest createStockRequest, HttpServletRequest req) {
      Long stockId = SimpleInMemoryNumericIdGenerator.generateUniqueId();
      Money money  = Money.parse(createStockRequest.getPrice());
      Stock stock  = new Stock(stockId,createStockRequest.getName(),money, LocalDateTime.now());
      return stockService.createNewStock(stock, req);
    }


}
