package org.example.Data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Data
@RequiredArgsConstructor
public class Client implements Comparable<Client>{
    private final String id;
    private final String name;
    private final String surname;
    private final BigInteger pesel;
    private final String city;

    @Override
    public int compareTo(Client o) {
        return this.id.compareTo(o.id);
    }
}
