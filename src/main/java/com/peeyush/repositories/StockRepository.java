package com.peeyush.repositories;

import com.peeyush.models.Stock;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * This interface defines all common operations for StockRepositories.
 */
@Repository
public interface StockRepository extends CrudRepository<Stock,Long>{

  /**
   * Get a stock by it's name.
   * @param name
   * @return Stock Object.
   */
  public Stock findByNameIgnoreCase(String name);

}
