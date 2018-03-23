package com.peeyush.controllers;

import com.peeyush.applications.StockApplication;
import com.peeyush.requests.CreateNewStockRequest;
import com.peeyush.requests.UpdateStockRequest;
import com.peeyush.dataTransferObjects.StockDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Stock Application APIs main controller.
 */
@RestController
@RequestMapping("api/")
@Api(value="Simple Stock App", description="Operations pertaining to stocks application")
public class StockController {

    @Autowired
    private StockApplication stockApplication;

    // Get All Stocks
    @ApiOperation(value = "Get all available stocks", response = ResponseEntity.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 500, message = "Internal Server Error"),
    }
    )
    @RequestMapping(value = "stocks", method = RequestMethod.GET)
    public ResponseEntity<List<StockDto>> getAllStocks() {
      return stockApplication.getAllStocksResponse();
    }

    // Get One Stock
    @ApiOperation(value = "Get one stock by Id", response = ResponseEntity.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved the desired stock by Id"),
        @ApiResponse(code = 404, message = "No stock exists corresponding to the given Stock Id"),
        @ApiResponse(code = 500, message = "Internal Server Error"),
    }
    )
    @RequestMapping(value = "stocks/{id}", method = RequestMethod.GET)
    public ResponseEntity<StockDto> getSingleStock(@PathVariable Long id) {
      return stockApplication.getSingleStockResponse(id);
    }

    // Create A New Stock
    @ApiOperation(value = "Create a new stock", response = ResponseEntity.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Successfully created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error"),
    }
    )
    @RequestMapping(value = "stocks", method = RequestMethod.POST)
    public ResponseEntity<StockDto> createNewStock(@Valid @RequestBody CreateNewStockRequest createNewStockRequest, HttpServletRequest req) {
      return stockApplication.createNewStock(createNewStockRequest, req);
    }

    // Update A Stock with PUT
    @ApiOperation(value = "Update any existing stock", response = ResponseEntity.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "No stock exists corresponding to the given Stock Id"),
        @ApiResponse(code = 500, message = "Internal Server Error"),
    }
    )
    @RequestMapping(value = "stocks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StockDto> putUpdateStock(@PathVariable Long id, @Valid @RequestBody UpdateStockRequest updateStockRequest) {
      return stockApplication.putUpdateStock(id,updateStockRequest);
    }


}
