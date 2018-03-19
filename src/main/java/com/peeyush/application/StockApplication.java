package com.peeyush.application;


import com.peeyush.models.Stock;
import com.peeyush.requests.CreateNewStockRequest;
import com.peeyush.transferObjects.StockTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface StockApplication {

  public ResponseEntity<List<StockTO>> getAllStocksResponse();

  public ResponseEntity<StockTO> getSingleStockResponse(Long id);

  public ResponseEntity<StockTO> createNewStock(CreateNewStockRequest createNewStockRequest, HttpServletRequest request);

  public ResponseEntity<StockTO> putUpdateStock(Long id, Stock stockUpdates);

}
