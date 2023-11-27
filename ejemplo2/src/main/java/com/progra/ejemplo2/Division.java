package com.progra.ejemplo2;

import java.util.Optional;

public class Division {

    public static Optional<Double> dividir(Integer x){
        if(x==0)
            return Optional.empty();
        else
            return Optional.of(1.0/x);
    }
}
