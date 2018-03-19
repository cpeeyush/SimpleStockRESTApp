package com.peeyush.repositories;

import com.peeyush.models.Stock;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
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
  public Stock fetchOneByName(String name) {
    //Not Efficient : We can make it more efficient by choosing a lookup data structure. But for MVP I am keeping it simple.
    for(Map.Entry<Long,Stock> entry : storage.entrySet()){
        if(entry.getValue().getName().equalsIgnoreCase(name)){
          return entry.getValue();
        }
    }
    return null;
  }

  @Override
  public List<Stock> fetchAll() {
    return new ArrayList<>(storage.values());
  }
}
