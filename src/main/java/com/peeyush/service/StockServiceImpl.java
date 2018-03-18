package com.peeyush.service;

import com.peeyush.exceptions.StockMissingInformationException;
import com.peeyush.exceptions.StockNotFoundException;
import com.peeyush.model.Stock;
import com.peeyush.repository.StockRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class StockServiceImpl implements StockService {

  private StockRepository stockRepository;

  @Autowired
  public StockServiceImpl(StockRepository stockRepository){
    Assert.notNull(stockRepository, "StockRepository must not be null");
    this.stockRepository = stockRepository;
  }

  @Override
  public ResponseEntity<List<Stock>> getAllStocksResponse() {
    List<Stock> stockList = stockRepository.fetchAll();
    return new ResponseEntity<>(stockList, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Stock> getSingleStockResponse(Long id) {
    Stock stock = findStockIfExists(id);
    return new ResponseEntity<>(stock,HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Stock> createNewStock(Stock stock, HttpServletRequest request) {

    if(null != stock.getId() && !StringUtils.isBlank(stock.getName()) && null != stock.getPrice()) {
      Stock newStock = stockRepository.save(stock);
      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.set("Location", stockUrlHelper(newStock, request));

      return new ResponseEntity<>(newStock, responseHeaders, HttpStatus.CREATED);
    } else {
      throw new StockMissingInformationException("You must include stock Id,Name & Price");
    }
  }

  @Override
  public ResponseEntity<Stock> putUpdateStock(Long id, Stock stockUpdates) {
    Stock existingStock = findStockIfExists(id);
    if(null != stockUpdates.getId() && StringUtils.isBlank(stockUpdates.getName()) && null != stockUpdates.getPrice()) {
      BeanUtils.copyProperties(stockUpdates,existingStock);
      Stock updatedStock = stockRepository.save(existingStock);
      return new ResponseEntity<>(updatedStock,HttpStatus.OK);
    }else {
      throw new StockMissingInformationException("You must include stock Id,Name & Price");
    }
  }

  private String stockUrlHelper(Stock stock, HttpServletRequest request) {
    StringBuilder resourcePath = new StringBuilder();

    resourcePath.append(request.getRequestURL());
    resourcePath.append("/");
    resourcePath.append(stock.getId());

    return resourcePath.toString();
  }


  private Stock findStockIfExists(Long id){
    Stock existingStock = stockRepository.fetchOne(id);

    if(null != existingStock) {
      return existingStock;
    } else {
      throw new StockNotFoundException("Stock Not Found");
    }
  }
}
