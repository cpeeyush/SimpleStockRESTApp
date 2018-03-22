package com.peeyush.controllers;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peeyush.applications.StockApplication;
import com.peeyush.dataTransferObjects.StockDto;
import com.peeyush.requests.CreateNewStockRequest;
import com.peeyush.requests.UpdateStockRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.hamcrest.MockitoHamcrest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(StockController.class)
public class StockControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private StockApplication stockApplication;
  private StockDto stockDto;

  @Before
  public void setUp(){
    stockDto = new StockDto();
    stockDto.setCurrentPrice("USD 23.47");
    stockDto.setId((long)122);
    stockDto.setName("APPLE");
    stockDto.setLastUpdate(LocalDateTime.now());
  }

  @Test
  public void whenGetAllStocks_thenReturnAllStock() throws Exception {
      List<StockDto> stockDtoList = new ArrayList<>();
      stockDtoList.add(stockDto);
      ResponseEntity<List<StockDto>> responseEntity = new ResponseEntity<>(stockDtoList,HttpStatus.OK);
      Mockito.when(stockApplication.getAllStocksResponse()).thenReturn(responseEntity);

      mockMvc.perform(get("/api/stocks")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
          .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is(stockDto.getName())))
          .andExpect(MockMvcResultMatchers.jsonPath("$[0].currentPrice", is(stockDto.getCurrentPrice())));
  }

  @Test
  public void whenGetSingleStock_thenReturnSingleStock() throws Exception {
    ResponseEntity<StockDto> responseEntity = new ResponseEntity<>(stockDto,HttpStatus.OK);
    Mockito.when(stockApplication.getSingleStockResponse(MockitoHamcrest.longThat(Matchers.any(Long.class)))).thenReturn(responseEntity);

    mockMvc.perform(get("/api/stocks/122")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(stockDto.getName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.currentPrice", is(stockDto.getCurrentPrice())));
  }

  @Test
  public void whenCreateNewStock_thenReturnCreatedStock() throws Exception {
    CreateNewStockRequest createNewStockRequest = new CreateNewStockRequest();
    createNewStockRequest.setName("APPLE");
    createNewStockRequest.setCurrentPrice("USD 23.47");
    ResponseEntity<StockDto> responseEntity = new ResponseEntity<>(stockDto,HttpStatus.CREATED);
    Mockito.when(stockApplication.createNewStock(MockitoHamcrest.argThat(Matchers.any(CreateNewStockRequest.class)),
        MockitoHamcrest.argThat(Matchers.any(HttpServletRequest.class)))).thenReturn(responseEntity);
    ObjectMapper objectMapper = new ObjectMapper();

    mockMvc.perform(post("/api/stocks")
        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(createNewStockRequest)))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(createNewStockRequest.getName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.currentPrice", is(createNewStockRequest.getCurrentPrice())));
  }

  @Test
  public void whenCreateNewStockWithBadData_thenReturnBadRequest() throws Exception {
    CreateNewStockRequest createNewStockRequest = new CreateNewStockRequest();
    ResponseEntity<StockDto> responseEntity = new ResponseEntity<>(stockDto,HttpStatus.CREATED);
    Mockito.when(stockApplication.createNewStock(MockitoHamcrest.argThat(Matchers.any(CreateNewStockRequest.class)),
        MockitoHamcrest.argThat(Matchers.any(HttpServletRequest.class)))).thenReturn(responseEntity);
    ObjectMapper objectMapper = new ObjectMapper();

    mockMvc.perform(post("/api/stocks")
        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(createNewStockRequest)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void whenUpdateAStock_thenReturnUpdatedStock() throws Exception {
    UpdateStockRequest updateStockRequest = new UpdateStockRequest();
    updateStockRequest.setCurrentPrice("USD 500.42");
    stockDto.setCurrentPrice("USD 500.42");
    ResponseEntity<StockDto> responseEntity = new ResponseEntity<>(stockDto,HttpStatus.OK);
    Mockito.when(stockApplication.putUpdateStock(MockitoHamcrest.argThat(Matchers.any(Long.class)),
        MockitoHamcrest.argThat(Matchers.any(UpdateStockRequest.class)))).thenReturn(responseEntity);
    ObjectMapper objectMapper = new ObjectMapper();

    mockMvc.perform(put("/api/stocks/122")
        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updateStockRequest)))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.currentPrice", is(updateStockRequest.getCurrentPrice())));
  }

  @Test
  public void whenUpdateAStockWithBadData_thenReturnBadRequest() throws Exception {
    UpdateStockRequest updateStockRequest = new UpdateStockRequest();
    ResponseEntity<StockDto> responseEntity = new ResponseEntity<>(stockDto,HttpStatus.OK);
    Mockito.when(stockApplication.putUpdateStock(MockitoHamcrest.argThat(Matchers.any(Long.class)),
        MockitoHamcrest.argThat(Matchers.any(UpdateStockRequest.class)))).thenReturn(responseEntity);
    ObjectMapper objectMapper = new ObjectMapper();

    mockMvc.perform(put("/api/stocks/122")
        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updateStockRequest)))
        .andExpect(status().isBadRequest());
  }

}
