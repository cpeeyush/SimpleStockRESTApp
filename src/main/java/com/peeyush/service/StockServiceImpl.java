package com.peeyush.service;

import com.peeyush.exceptions.StockAlreadyExistsException;
import com.peeyush.exceptions.StockMissingInformationException;
import com.peeyush.exceptions.StockNotFoundException;
import com.peeyush.models.Stock;
import com.peeyush.repositories.StockRepository;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
  public List<Stock> getAllStocks() {
    List<Stock> stockList = stockRepository.fetchAll();
    return stockList;
  }

  @Override
  public Stock getSingleStock(Long id) {
    Stock stock = findStockIfExists(id);
    return stock;
  }

  @Override
  public Stock createNewStock(Stock stock) {
    if(null != stock.getId() && !StringUtils.isBlank(stock.getName()) && null != stock.getMoney()) {
      Stock newStock = createStockIfNotAlreadyExists(stock);
      return newStock;
    } else {
      throw new StockMissingInformationException("You must include stock Id,Name & Price");
    }
  }

  @Override
  public Stock putUpdateStock(Long id, Stock stockUpdates) {
    Stock existingStock = findStockIfExists(id);
    if(null != stockUpdates.getId() && StringUtils.isBlank(stockUpdates.getName()) && null != stockUpdates.getMoney()) {
      BeanUtils.copyProperties(stockUpdates,existingStock);
      Stock updatedStock = stockRepository.save(existingStock);
      return updatedStock;
    }else {
      throw new StockMissingInformationException("You must include stock Id,Name & Price");
    }
  }

  private Stock findStockIfExists(Long id){
    Stock existingStock = stockRepository.fetchOne(id);

    if(null != existingStock) {
      return existingStock;
    } else {
      throw new StockNotFoundException("Stock Not Found");
    }
  }

  private Stock createStockIfNotAlreadyExists(Stock stock){
    Stock existingStock = stockRepository.fetchOneByName(stock.getName());

    if(null != existingStock){
      throw new StockAlreadyExistsException("Stock Already Exists");
    }

    return stockRepository.save(stock);
  }
}
