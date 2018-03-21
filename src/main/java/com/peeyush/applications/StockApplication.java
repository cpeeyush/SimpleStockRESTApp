package com.peeyush.applications;


import com.peeyush.requests.CreateNewStockRequest;
import com.peeyush.requests.UpdateStockRequest;
import com.peeyush.dataTransferObjects.StockDto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

/**
 * Main purpose of this class is to abstract the ResponseEntity creation for various use cases.
 * It provides all the methods which returns ResponseEntity and could be used by spring controller directly.
 * Methods are self explanatory.
 */
public interface StockApplication {

  public ResponseEntity<List<StockDto>> getAllStocksResponse();

  public ResponseEntity<StockDto> getSingleStockResponse(Long id);

  public ResponseEntity<StockDto> createNewStock(CreateNewStockRequest createNewStockRequest, HttpServletRequest request);

  public ResponseEntity<StockDto> putUpdateStock(Long id, UpdateStockRequest updateStockRequest);

}
