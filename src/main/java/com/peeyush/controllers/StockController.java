package com.peeyush.controllers;

import com.peeyush.application.StockApplication;
import com.peeyush.requests.CreateNewStockRequest;
import com.peeyush.requests.UpdateStockRequest;
import com.peeyush.transferObjects.StockTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    private StockApplication stockApplication;

    // List All Stocks
    @RequestMapping(value = "stocks", method = RequestMethod.GET)
    public ResponseEntity<List<StockTO>> getAllStocks() {
      return stockApplication.getAllStocksResponse();
    }

    // List One Stock
    @RequestMapping(value = "stocks/{id}", method = RequestMethod.GET)
    public ResponseEntity<StockTO> getSingleStock(@PathVariable Long id) {
      return stockApplication.getSingleStockResponse(id);
    }

    // Create A New Stock
    @RequestMapping(value = "stocks", method = RequestMethod.POST)
    public ResponseEntity<StockTO> createNewStock(@Valid @RequestBody CreateNewStockRequest createNewStockRequest, HttpServletRequest req) {
      return stockApplication.createNewStock(createNewStockRequest, req);
    }

    // Update Contact with PUT
    @RequestMapping(value = "stocks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StockTO> putUpdateStock(@PathVariable Long id, @Valid @RequestBody UpdateStockRequest updateStockRequest) {
      Money money = Money.parse(updateStockRequest.getCurrentPrice());
      //Stock stock = new Stock(updateStockRequest.getId(),);
      //return contactService.putUpdateContact(id, contact);
      return null;
    }


}
