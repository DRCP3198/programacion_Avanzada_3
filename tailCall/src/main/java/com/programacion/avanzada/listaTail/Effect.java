package com.programacion.avanzada.listaTail;

    @FunctionalInterface
    public interface Effect<T> {
        void apply(T t);
    }


