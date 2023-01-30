package org.example.Services;

import org.example.Data.Money;
import org.example.Data.Product;
import org.example.Data.Purchase;

import java.util.List;

public class LevelFirstImpl implements LevelFirst {

    @Override
    public int sumClients(List<Purchase> purchaseList) {
        return (int) purchaseList.stream().map(Purchase::getBuyer).distinct().count();
    }

    @Override
    public int sumClientsPayingByBlik(List<Purchase> purchaseList) {
        return (int) purchaseList.stream()
                .filter(e -> Purchase.Payment.BLIK.equals(e.getPayment()))
                .map(Purchase::getBuyer)
                .distinct()
                .count();
    }

    @Override
    public int sumClientsPayingByCreditCard(List<Purchase> purchaseList) {
        return (int) purchaseList.stream()
                .filter(e -> Purchase.Payment.CREDIT_CARD.equals(e.getPayment()))
                .map(Purchase::getBuyer)
                .distinct()
                .count();

    }

    @Override
    public int sumPurchasePayedByEUR(List<Purchase> purchaseList) {
        return (int) purchaseList.stream()
                .map(Purchase::getProduct)
                .map(Product::getPrice)
                .filter(e -> Money.Currency.EUR.equals(e.getCurrency())).count();
    }

    @Override
    public int sumOfUniqueProductPayedByEUR(List<Purchase> purchaseList) {
        return (int) purchaseList.stream()
                .map(Purchase::getProduct)
                .distinct()
                .map(Product::getPrice)
                .filter(e -> Money.Currency.EUR.equals(e.getCurrency()))
                .count();
    }
}
