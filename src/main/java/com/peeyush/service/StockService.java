package com.peeyush.service;

import com.peeyush.models.Stock;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

/**
 * This Interface Defines all required methods for any stock service.
 */
public interface StockService {

  /**
   * Get All the Stock.
   * @return List of Stock.
   */
  public List<Stock> getAllStocks();

  /**
   * Get a Single Stock by Id.
   * @param id
   * @return Stock Entity.
   */
   public Stock getSingleStock(Long id);

  /**
   * Create a new stock record.
   * @param stock
   * @return Created Stock Entity
   */
   public Stock createNewStock(Stock stock);

  /**
   * Update an existing stock record.(Only Price)
   * @param id
   * @param stockUpdates
   * @return Updated Stock Entity
   */
   public Stock putUpdateStock(Long id, Stock stockUpdates);

}
