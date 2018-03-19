package com.peeyush.repositories;

import com.peeyush.models.Stock;
import java.util.List;


/**
 * This interface defines all common operations for StockRepositories.
 */

public interface StockRepository {

  /**
   * Save a new stock.
   * @param stock
   * @return Newly updated Stock.
   */
  public Stock save(Stock stock);

  /**
   * Fetch a stock by it's ID.
   * @param id
   * @return Stock Object.
   */
  public Stock fetchOne(Long id);

  /**
   * Fetch a stock by it's name.
   * @param name
   * @return Stock Object.
   */
  public Stock fetchOneByName(String name);

  /**
   * @return List of All Stocks from Data Store.
   */
  public List<Stock> fetchAll();
}
