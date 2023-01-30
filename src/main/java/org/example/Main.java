package org.example;

import org.example.Data.DataFactory;
import org.example.Data.Purchase;
import org.example.Services.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Purchase> purchaseList = DataFactory.produce();
        LevelFirst levelFirst = new LevelFirstImpl();
        LevelSecond levelSecond = new LevelSecondImpl();
        levelThird levelThird = new LevelThirdImpl();

//        System.out.println(levelFirst.sumClients(purchaseList));
//        System.out.println(levelFirst.sumClientsPayingByBlik(purchaseList));
//        System.out.println(levelFirst.sumClientsPayingByCreditCard(purchaseList));
//        System.out.println(levelFirst.sumPurchasePayedByEUR(purchaseList));
//        System.out.println(levelFirst.sumOfUniqueProductPayedByEUR(purchaseList));


//        System.out.println(levelSecond.MapWithClientsSum(purchaseList));
//        System.out.println(levelSecond.MapWithClientsSumOfProduct(purchaseList, "HOBBY"));
//        System.out.println(levelSecond.updateStatusOfPurchase(purchaseList));
//        System.out.println(levelSecond.numberOfClientsWhoBuysProductInEUR(purchaseList));
//        levelSecond.numberOfClientsWhoBuysProductInEUR(purchaseList);
//        levelSecond.MapSortedByAgeAndBoughtProducts(purchaseList);
//        levelSecond.MapAgeAndCategory(purchaseList);
//        levelSecond.secondMostPurchasedProduct(purchaseList);

//        levelThird.structureForPeopleOver50age(purchaseList);
        levelThird.ageWithMostBuyestProduct(purchaseList);
    }
}