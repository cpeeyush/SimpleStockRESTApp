package com.peeyush.application;


import com.peeyush.requests.CreateNewStockRequest;
import com.peeyush.requests.UpdateStockRequest;
import com.peeyush.dataTransferObjects.StockDto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface StockApplication {

  public ResponseEntity<List<StockDto>> getAllStocksResponse();

  public ResponseEntity<StockDto> getSingleStockResponse(Long id);

  public ResponseEntity<StockDto> createNewStock(CreateNewStockRequest createNewStockRequest, HttpServletRequest request);

  public ResponseEntity<StockDto> putUpdateStock(Long id, UpdateStockRequest updateStockRequest);

}
