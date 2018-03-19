package com.peeyush.service;

import com.peeyush.exceptions.StockAlreadyExistsException;
import com.peeyush.exceptions.StockMissingInformationException;
import com.peeyush.exceptions.StockNotFoundException;
import com.peeyush.models.Stock;
import com.peeyush.repositories.StockRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockServiceImpl implements StockService {

  @Autowired
  private StockRepository stockRepository;

  @Override
  public List<Stock> getAllStocks() {
    return stockRepository.fetchAll();
  }

  @Override
  public Stock getSingleStock(Long id) {
    return findStockIfExists(id);
  }

  @Override
  public Stock createNewStock(Stock stock) {
    if(null != stock.getId() && !StringUtils.isBlank(stock.getName()) && null != stock.getMoney()) {
      return createStockIfNotAlreadyExists(stock);
    } else {
      throw new StockMissingInformationException("You must include stock Id,Name & Price");
    }
  }

  @Override
  public Stock putUpdateStock(Long id, Stock stockUpdates) {
    Stock existingStock = findStockIfExists(id);
    if(null != stockUpdates.getId() && StringUtils.isBlank(stockUpdates.getName()) && null != stockUpdates.getMoney()) {
      BeanUtils.copyProperties(stockUpdates,existingStock);
      existingStock.setLastUpdate(LocalDateTime.now());
      return stockRepository.save(existingStock);
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
    stock.setLastUpdate(LocalDateTime.now());
    return stockRepository.save(stock);
  }
}
