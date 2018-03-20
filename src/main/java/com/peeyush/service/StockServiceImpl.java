package com.peeyush.service;

import com.peeyush.exceptions.StockAlreadyExistsException;
import com.peeyush.exceptions.StockMissingInformationException;
import com.peeyush.exceptions.StockNotFoundException;
import com.peeyush.models.Stock;
import com.peeyush.repositories.StockRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    List<Stock> stockList    = new ArrayList<>();
    Iterable<Stock> iterable = stockRepository.findAll();
    iterable.forEach(stockList::add);
    return stockList;
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

    if(existingStock.getId().equals(stockUpdates.getId())
        && null != stockUpdates.getId()
        && !StringUtils.isBlank(stockUpdates.getName())
        && null != stockUpdates.getMoney()) {

      return stockRepository.save(stockUpdates);
    }else {
      throw new StockMissingInformationException("You must include stock Id,Name & Price");
    }
  }

  private Stock findStockIfExists(Long id){
    Optional<Stock> optional = stockRepository.findById(id);
    if(!optional.isPresent()){
      throw new StockNotFoundException("Stock Not Found");
    }
    return optional.get();
  }

  private Stock createStockIfNotAlreadyExists(Stock stock){
    Stock existingStock = stockRepository.findByNameIgnoreCase(stock.getName());

    if(null != existingStock){
      throw new StockAlreadyExistsException("Stock Already Exists");
    }
    return stockRepository.save(stock);
  }
}
