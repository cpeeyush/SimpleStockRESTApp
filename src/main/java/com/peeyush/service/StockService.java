package com.peeyush.service;

import com.peeyush.model.Stock;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

/**
 * This Interface Defines all required methods for any stock service.
 */
public interface StockService {

  /**
   * Get All the Stock.
   * @return Response Entity of java.util.List of Stock.
   */
  public ResponseEntity<List<Stock>> getAllStocksResponse();

  /**
   * Get a Single Stock by Id.
   * @param id
   * @return Response Entity of Stock.
   */
   public ResponseEntity<Stock> getSingleStockResponse(Long id);

  /**
   * Create a new stock record.
   * @param stock
   * @param request
   * @return Response Entity of Created Stock.
   */
   public ResponseEntity<Stock> createNewStock(Stock stock, HttpServletRequest request);

  /**
   * Update an existing stock record.
   * @param id
   * @param stockUpdates
   * @return Response Entity of Updated Stock.
   */
   public ResponseEntity<Stock> putUpdateStock(Long id, Stock stockUpdates);

}
