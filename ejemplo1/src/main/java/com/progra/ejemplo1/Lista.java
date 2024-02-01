package com.progra.ejemplo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Lista<T> {

    private T cabecera;
    private Lista<T> cola;

    public Lista(T cabecera) {
        this.cabecera = cabecera;
        this.cola = null;
    }

    public Lista(T cabecera, Lista<T> cola) {
        this.cabecera = cabecera;
        this.cola = cola;
    }

    public Lista(T... elementos) {
        if (elementos.length > 0) {
            this.cabecera = elementos[0];
            if (elementos.length > 1) {
                this.cola = new Lista<T>(Arrays.copyOfRange(elementos, 1, elementos.length));
            } else {
                this.cola = null;
            }
        }
    }

    public Lista<T> eliminar(T elemento) {
        if (this.cabecera.equals(elemento)) {
            return this.cola;
        } else if (this.cola != null) {
            Lista<T> nuevaCola = this.cola.eliminar(elemento);
            return new Lista<T>(this.cabecera, nuevaCola);
        } else {
            return this;
        }
    }

    /*public Lista<T> filtrarPares() {
        if (this.cabecera%2==0) {
            if (this.cola != null) {
                return new Lista<T>(this.cabecera, this.cola.filtrarPares());
            } else {
                return new Lista<T>(this.cabecera);
            }
        } else {
            if (this.cola != null) {
                return this.cola.filtrarPares();
            } else {
                return null;
            }
        }
    }*/

    public Lista<T> invertir() {
        if (this.cola != null) {
            Lista<T> invertida = this.cola.invertir();
            invertida = invertida.add(this.cabecera);
            return invertida;
        } else {
            return new Lista<T>(this.cabecera);
        }
    }

    public Lista<T> add(T elemento) {
        if (this.cabecera == null) {
            return new Lista<T>(elemento);
        } else {
            return new Lista<T>(this.cabecera, this.cola.add(elemento));
        }
    }

    public void imprimir() {
        System.out.print(this.cabecera);
        if (this.cola != null) {
            this.cola.imprimir();
        }
    }
}
