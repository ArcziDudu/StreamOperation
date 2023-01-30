package org.example.Services;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Pair <U,V>{
    private final U u;
    private  final V v;
}
