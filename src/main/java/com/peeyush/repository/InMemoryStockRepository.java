package com.peeyush.repository;

import com.peeyush.model.Stock;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStockRepository implements StockRepository {

  private static Map<Long,Stock> storage = new ConcurrentHashMap<>();

  @Override
  public Stock save(Stock stock) {
    storage.put(stock.getId(),stock);
    return stock;
  }

  @Override
  public Stock fetchOne(Long id) {
    return storage.get(id);
  }

  @Override
  public List<Stock> fetchAll() {
    return new ArrayList<>(storage.values());
  }
}
