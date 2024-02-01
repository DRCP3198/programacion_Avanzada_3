package com.progra.ejemplo3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Principal {


    public static void main(String[] args) {
        int percent1=5;
        int percent2=9;
        final int percent3=12;
        List<Integer> numeros= new ArrayList<>();
        numeros.add(12);
        numeros.add(13);

        //COMPOSICION DE FUNCIONES
        ComposicionFunciones funcion1 = new ComposicionFunciones();
        Integer res1=funcion1.fg.apply(5);
        System.out.println("Resultado de hacer f o g: "+res1);

        ComposicionFunciones funcion2 = new ComposicionFunciones();
        Integer res2=funcion1.gf.apply(5);
        System.out.println("Resultado de hacer g o f: "+res2);
        //CUALES SON METODOS FUNCIONALES
        MetodosFuncionales metodosFuncionales= new MetodosFuncionales();
        //1.Si es un metodo Funcional
        Integer a=metodosFuncionales.add(5,2);
        System.out.println(a);
        //2.Si es un metodo Funcional
        Integer b=metodosFuncionales.mult(2,3);
        System.out.println(b);
        //3.No es un metodo Funcional
        Integer c=metodosFuncionales.div(1,1);
        System.out.println(c);
        //4.No es un metodo Funcional
        Integer d=metodosFuncionales.applyTax1(percent1);
        System.out.println(d);
        //5.No es un metodo Funcional
        Integer e=metodosFuncionales.applyTax2(percent2);
        System.out.println(e);
        //6.Si es un metodo Funcional
        Integer f=metodosFuncionales.applyTax2(percent3);
        System.out.println(f);
        //7.No es un metodo Funcional
        List<Integer> lista=metodosFuncionales.append(25,numeros);
        System.out.println(lista);




    }
}
