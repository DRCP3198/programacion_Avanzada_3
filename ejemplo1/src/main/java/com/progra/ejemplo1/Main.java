package com.progra.ejemplo1;

public class Main {
    public static void main(String[] args) {
        Lista<Integer> lista = new Lista<Integer>(1,2,3,4,5,6);

        lista.imprimir();

        lista = lista.eliminar(3);

        lista.imprimir();

    }
}
