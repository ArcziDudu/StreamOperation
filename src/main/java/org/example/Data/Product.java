package org.example.Data;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Data
public class Product implements Comparable<Product>{
    private final String id;
    private final String name;
    private final Category category;
    private final Money price;

    @Override
    public int compareTo(final Product o) {
        int thisNumber = Integer.parseInt(id.substring(7));
        int otherNumber = Integer.parseInt(o.id.substring(7));
        return thisNumber - otherNumber;
    }
    public enum Category {
        HOBBY,
        CLOTHES,
        GARDEN,
        AUTOMOTIVE
    }

}
