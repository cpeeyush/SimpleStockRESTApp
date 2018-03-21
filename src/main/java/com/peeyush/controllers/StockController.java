package com.peeyush.controllers;

import com.peeyush.applications.StockApplication;
import com.peeyush.requests.CreateNewStockRequest;
import com.peeyush.requests.UpdateStockRequest;
import com.peeyush.dataTransferObjects.StockDto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Stock Application APIs main controller.
 */
@RestController
@RequestMapping("api/")
public class StockController {

    @Autowired
    private StockApplication stockApplication;

    // Get All Stocks
    @RequestMapping(value = "stocks", method = RequestMethod.GET)
    public ResponseEntity<List<StockDto>> getAllStocks() {
      return stockApplication.getAllStocksResponse();
    }

    // Get One Stock
    @RequestMapping(value = "stocks/{id}", method = RequestMethod.GET)
    public ResponseEntity<StockDto> getSingleStock(@PathVariable Long id) {
      return stockApplication.getSingleStockResponse(id);
    }

    // Create A New Stock
    @RequestMapping(value = "stocks", method = RequestMethod.POST)
    public ResponseEntity<StockDto> createNewStock(@Valid @RequestBody CreateNewStockRequest createNewStockRequest, HttpServletRequest req) {
      return stockApplication.createNewStock(createNewStockRequest, req);
    }

    // Update A Stock with PUT
    @RequestMapping(value = "stocks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StockDto> putUpdateStock(@PathVariable Long id, @Valid @RequestBody UpdateStockRequest updateStockRequest) {
      return stockApplication.putUpdateStock(id,updateStockRequest);
    }


}
