package org.example.Services;

import org.example.Data.Client;
import org.example.Data.Purchase;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

public interface LevelSecond {
    TreeMap<String, BigDecimal> MapWithClientsSum(List<Purchase> purchaseList);
    TreeMap<Client, Long> MapWithClientsSumOfProduct(List<Purchase> purchaseList, String category);

    long updateStatusOfPurchase(List<Purchase> purchaseList);
    void numberOfClientsWhoBuysProductInEUR(List<Purchase> purchaseList);
    void MapSortedByAgeAndBoughtProducts(List<Purchase> purchaseList);

    void MapAgeAndCategory(List<Purchase> purchaseList);
    void secondMostPurchasedProduct(List<Purchase> purchaseList);
}
