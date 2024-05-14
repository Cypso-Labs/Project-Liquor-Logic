package com.liquorlogic.inventoryservice.service;

import com.liquorlogic.inventoryservice.entity.Stock;

import java.util.List;
import java.util.UUID;

/**
 * @author Nipuna Ruwan.
 *  
 */
public interface StockService {
    List<Stock> getAllStocks();
    Stock getStockById(UUID stockId);
    Stock createStock(Stock stock);
    void deleteStock(UUID stockId);
}
