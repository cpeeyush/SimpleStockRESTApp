package com.peeyush.repository;

import com.peeyush.model.Stock;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * This interface defines all common operations for StockRepositories.
 */
@Repository
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
   * @return List of All Stocks from Data Store.
   */
  public List<Stock> fetchAll();
}
