package org.example.Services;

import com.sun.source.doctree.SeeTree;
import org.example.Data.Product;
import org.example.Data.Purchase;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LevelThirdImpl implements levelThird {
    static final Integer CURRENT_YEAR = 2020;

    @Override
    public void structureForPeopleOver50age(List<Purchase> purchaseList) {
        Map<String, Map<Product.Category, Long>> collectFirst = purchaseList.stream()
                .filter(p -> CURRENT_YEAR - (1900 + Integer.parseInt(p.getBuyer().getPesel().toString().substring(0, 2))) > 50)
                .collect(Collectors.groupingBy(
                        p -> p.getBuyer().getPesel().toString().substring(0, 2),
                        Collectors.groupingBy(
                                p -> p.getProduct().getCategory(),
                                Collectors.counting()
                        )
                ));
             var collectTwo = collectFirst.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Arrays.stream(Product.Category.values())
                                .collect(Collectors.toMap(
                                        categoryKey -> e.getValue().getOrDefault(categoryKey, 0L),
                                        List::of,
                                        (currentList, nextList) -> Stream.concat(currentList.stream(), nextList.stream())
                                                .toList(),
                                        TreeMap::new
                                ))
                ));
        Map<String, Map.Entry<Long, List<Product.Category>>> yearWithMinCategoryentry = collectTwo.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().entrySet().stream()
                                .min(Comparator.comparing(
                                        Map.Entry::getKey
                                ))
                                .get()
                ));
        printingMethod.printing(yearWithMinCategoryentry);
    }

    @Override
    public void ageWithMostBuyestProduct(List<Purchase> purchaseList) {
        Map<String, Long> quantityByYear = purchaseList.stream().collect(Collectors.groupingBy(
                p -> p.getBuyer().getPesel().toString().substring(0, 2),
                Collectors.mapping(
                        Purchase::getQuantity,
                        Collectors.reducing(0L, Long::sum)
                )
        ));
        Map<Long, List<String>> collect = quantityByYear.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getValue,
                        e -> List.of(e.getKey()),
                        (currentList, nextList) -> Stream.concat(currentList.stream(), nextList.stream()).toList(),
                        TreeMap::new
                ));
        Map.Entry<Long, List<String>> noPurchases = collect.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getKey))
                .orElseThrow(() -> new RuntimeException("no purchases"));
        System.out.printf("most number of products (%s) was bought by people from years (%s)", noPurchases.getKey(), noPurchases.getValue());
    }
}
