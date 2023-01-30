package org.example.Data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@RequiredArgsConstructor
@Data
public class Money {
    private final BigDecimal value;
    private final Currency currency;

    public enum Currency {
        PLN,
        EUR
    }
}
