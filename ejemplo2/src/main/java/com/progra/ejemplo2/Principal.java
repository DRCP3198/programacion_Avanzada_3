package com.progra.ejemplo2;

import java.util.Optional;
import java.util.function.Function;

public class Principal {

    public static void main(String[] args) {

            //FORMA 1
            var resultado= Division.dividir(2);
            if(resultado.isPresent()){
                System.out.println("La divicion es: "+ resultado.get());
            }else {
                System.out.println("No existe la divicion FORMA 1");
            }

            //FORMA2
            Function<Integer, Optional<Integer>> fn= x->{
                if(x==0){
                    return Optional.empty();
                }
                else{
                    return Optional.of(1/x);
                }
            };
            var ret =fn.apply(0);
            if(ret.isPresent()) {
                System.out.printf("la division es: %.2f ", ret.get());
            }else {
                System.out.println("No existe la division para 0 FORMA 2");
            }


        }
    }

