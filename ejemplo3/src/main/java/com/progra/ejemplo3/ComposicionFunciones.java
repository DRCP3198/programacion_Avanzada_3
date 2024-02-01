package com.progra.ejemplo3;

import java.util.function.Function;

public class ComposicionFunciones {
    Function<Integer, Integer> f=x->x+2;
    Function<Integer, Integer> g=x->x*2;

    Function<Integer,Integer> fg = f.compose(g);
    Function<Integer,Integer> gf= g.compose(f);

}
