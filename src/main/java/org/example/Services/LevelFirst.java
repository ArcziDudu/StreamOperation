package org.example.Services;

import org.example.Data.Purchase;

import java.util.List;

public interface LevelFirst {
    int sumClients(List<Purchase> purchaseList);

    int sumClientsPayingByBlik(List<Purchase> purchaseList);

    int sumClientsPayingByCreditCard(List<Purchase> purchaseList);

    int sumPurchasePayedByEUR(List<Purchase> purchaseList);

    int sumOfUniqueProductPayedByEUR(List<Purchase> purchaseList);

}
