package com.peeyush.application;

import com.peeyush.models.Stock;
import com.peeyush.requests.CreateNewStockRequest;
import com.peeyush.requests.UpdateStockRequest;
import com.peeyush.service.StockService;
import com.peeyush.dataTransferObjects.StockDto;
import com.peeyush.utils.SimpleInMemoryNumericIdGenerator;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class StockApplicationImpl implements StockApplication {

  @Autowired
  private StockService stockService;

  @Override
  public ResponseEntity<List<StockDto>> getAllStocksResponse() {
    List<Stock> stockList     = stockService.getAllStocks();
    List<StockDto> stockDtoList = new ArrayList<>();
    stockList.forEach(stock -> {
      stockDtoList.add(new StockDto(stock));});
    return new ResponseEntity<>(stockDtoList, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<StockDto> getSingleStockResponse(Long id) {
    Stock stock = stockService.getSingleStock(id);
    return new ResponseEntity<>(new StockDto(stock),HttpStatus.OK);
  }

  @Override
  public ResponseEntity<StockDto> createNewStock(CreateNewStockRequest createNewStockRequest, HttpServletRequest request) {
    Long stockId = SimpleInMemoryNumericIdGenerator.generateUniqueId();
    Money money  = Money.parse(createNewStockRequest.getCurrentPrice());
    Stock newStock = stockService.createNewStock(new Stock(stockId, createNewStockRequest.getName(),money));
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Location", stockUrlHelper(newStock, request));
    return new ResponseEntity<>(new StockDto(newStock), responseHeaders, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<StockDto> putUpdateStock(Long id, UpdateStockRequest updateStockRequest) {
    Stock existingStock = stockService.getSingleStock(id);
    Money updateMoney   = Money.parse(updateStockRequest.getCurrentPrice());
    Stock updatedStock  = new Stock(id,existingStock.getName(),updateMoney);
    updatedStock        = stockService.putUpdateStock(id,updatedStock);
    return new ResponseEntity<>(new StockDto(updatedStock),HttpStatus.OK);
  }


  private String stockUrlHelper(Stock stock, HttpServletRequest request) {
    StringBuilder resourcePath = new StringBuilder();

    resourcePath.append(request.getRequestURL());
    resourcePath.append("/");
    resourcePath.append(stock.getId());

    return resourcePath.toString();
  }

}
