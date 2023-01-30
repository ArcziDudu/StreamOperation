package org.example.Services;

import org.example.Data.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LevelSecondImpl implements LevelSecond {
    @Override
    public TreeMap<String, BigDecimal> MapWithClientsSum(List<Purchase> purchaseList) {
        return purchaseList.stream()
                .filter(e -> Money.Currency.PLN.equals(e.getProduct().getPrice().getCurrency()))
                .collect(Collectors.groupingBy(
                        e -> e.getBuyer().getId(),
                        TreeMap::new,
                        Collectors.mapping(
                                p -> p.getProduct().getPrice().getValue().multiply(BigDecimal.valueOf(p.getQuantity())),
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));
    }

    @Override
    public TreeMap<Client, Long> MapWithClientsSumOfProduct(List<Purchase> purchaseList, String category) {
        Map<Client, List<Purchase>> listMap = purchaseList.stream()
                .filter(e -> e.getProduct().getCategory().equals(Product.Category.valueOf(category)))
                .filter(purchase -> purchase.getQuantity() > 1).collect(Collectors.groupingBy(Purchase::getBuyer));

        return listMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .map(Purchase::getQuantity)
                                .reduce(0L, Long::sum),
                        Long::sum,
                        TreeMap::new
                ));
    }

    @Override
    public long updateStatusOfPurchase(List<Purchase> purchaseList) {
        System.out.println("list of status: products");
        Map<Purchase.Status, List<Purchase>> collect = purchaseList.stream()
                .map(purchase -> new Purchase(purchase, OrderService.checkOrderStatus(purchase)))
                .collect(Collectors.toMap(
                        Purchase::getStatus,
                        List::of,
                        (List<Purchase> cL, List<Purchase> nL) -> Stream.concat(cL.stream(), nL.stream()).toList()));
        printingMethod.printing(collect);

        return purchaseList.stream()
                .map(purchase -> new Purchase(purchase, OrderService.checkOrderStatus(purchase)))
                .filter(p -> Purchase.Status.DONE.equals(p.getStatus()))
                .count();

    }

    @Override
    public void numberOfClientsWhoBuysProductInEUR(List<Purchase> purchaseList) {
        List<Purchase> purchases = purchaseList.stream()
                .filter(p -> Money.Currency.EUR.equals(p.getProduct().getPrice().getCurrency()))
                .toList();
        long count = purchases.stream().map(Purchase::getBuyer).distinct().count();
        System.out.println("liczba unikalnych osób, które zakupiły w euro:  " + count);

        Map<String, List<Purchase>> map = purchases.stream().collect(Collectors.groupingBy(
                p -> p.getBuyer().getId()
        ));
        printingMethod.printing(map);
    }

    @Override
    public void MapSortedByAgeAndBoughtProducts(List<Purchase> purchaseList) {
        Map<String, List<Purchase>> collect = purchaseList.stream().collect(Collectors.toMap(
                purchase -> purchase.getBuyer().getPesel().toString().substring(0, 2),
                List::of,
                (List<Purchase> cL, List<Purchase> nL) -> Stream.concat(cL.stream(), nL.stream()).toList(),
                TreeMap::new));
        printingMethod.printing(collect);
    }

    @Override
    public void MapAgeAndCategory(List<Purchase> purchaseList) {
        TreeMap<String, List<Product.Category>> collect = purchaseList.stream().collect(Collectors.groupingBy(
                purchase -> purchase.getBuyer().getPesel().toString().substring(0, 2),
                TreeMap::new,
                Collectors.mapping(purchase -> purchase.getProduct().getCategory(), Collectors.toList())
        ));
        printingMethod.printing(collect);
        //d
    }

    @Override
    public void secondMostPurchasedProduct(List<Purchase> purchaseList) {
        TreeMap<String, Long> collect = purchaseList.stream().collect(Collectors.groupingBy(
                p -> p.getProduct().getId(),
                TreeMap::new,
                Collectors.mapping(Purchase::getQuantity, Collectors.reducing(0L, Long::sum))
        ));
        printingMethod.printing(collect);

        Comparator<? super Pair<String, Long>> comparator = Comparator
                .comparing((Function<Pair<String, Long>, Long>) Pair::getV)
                .reversed()
                .thenComparing(Pair::getU);

        Pair<String, Long> aDefault = collect.entrySet().stream()
                .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                .sorted(comparator)
                .skip(1)
                .findFirst()
                .orElse(new Pair<>("Default", 0L));
        System.out.println(aDefault);
    }
}


