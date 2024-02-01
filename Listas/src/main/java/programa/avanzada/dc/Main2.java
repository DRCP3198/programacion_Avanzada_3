package programa.avanzada.dc;

import java.util.function.Function;

public class Main2 {
    public static void main(String[] args) {
        var ls = Lista.of(1, 2, 3, 4, 5);
        System.out.println(ls);
        System.out.println("prepend" + ls.prepend(99));
        System.out.println("append" + ls.append(12));
        System.out.println("insert" + ls.insert(2, 108));
        System.out.println("get " + ls.get(3));
        System.out.println("take(2) " + ls.take(2));
        System.out.println("take(20) " + ls.take(20));
        System.out.println("take(2) " + Lista.Empty.take(2));
        System.out.println("drop(2) " + ls.drop(2));
        System.out.println("drop(20) " + ls.drop(20));
        System.out.println("drop(20) " + ls.concat(Lista.of(6, 7, 8)));
        ;
        System.out.println();
//        var ls3 = Lista.of("aa","bb","cc");
//        System.out.println(ls3);
//       // System.out.println(ls3.count());
//        System.out.println( ls3.append("xyz"));

        var nuevaLista = ls.map(x -> x * 1.2);
        System.out.println("map lista original: " + ls);
        System.out.println("map lista nueva: " + nuevaLista);

        System.out.println("folding left to right: " + ls.foldingLeft(0, x -> y -> x + y));
        System.out.println("folding left to right max: " + ls.foldingLeft(ls.head(), x -> y -> Math.max(x, y)));
        System.out.println("folding left to right concat: " + ls.foldingLeft("", s -> x -> s + String.valueOf(x)));
        System.out.println("invertir lista: " + ls.invertFolding());
        System.out.println("folding map: " + ls.mapFolding(t -> "x" + t));
        System.out.println("folding map2: " + ls.mapFolding2(t -> "x" + t));
        System.out.println("folding count: " + ls.countFolding());
        System.out.println("folding append: " + ls.appendFolding(99));
        System.out.println("reduce: " + ls.reduce(0, x -> y -> x + y));
        System.out.println("reduce2: " + ls.reduce2(x -> y -> x + y));
        System.out.println("teke folding: " + ls.takeFolding(2));
        System.out.println("drop folding: " + ls.dropFold(3));
/////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("###################################################################");
        Effect<Integer> printInteger = x -> {
            System.out.println(x);
        };

        Executable neutro = () -> {
        };

        //Executable version1
        Function<Executable, Function<Integer, Executable>> fn =
                ex -> elem -> () -> {
                    ex.exec();
                    printInteger.apply(elem);
                };

        //Executable version2
        Function<Executable, Function<Integer, Executable>> fn1 =
                ex -> elem -> {
                    ex.exec();
                    return () -> printInteger.apply(elem);
                };

        System.out.println("Executable version1");
        var ex11 = fn.apply(neutro).apply(1);
        var ex22 = fn.apply(ex11).apply(2);
        var ex33 = fn.apply(ex22).apply(3);
        var ex44 = fn.apply(ex33).apply(4);
        ex44.exec();

        System.out.println("Executable version2");
        var ex1 = fn1.apply(neutro).apply(1);
        var ex2 = fn1.apply(ex1).apply(2);
        var ex3 = fn1.apply(ex2).apply(3);
        var ex4 = fn1.apply(ex3).apply(4);
        fn1.apply(ex4).apply(5);


        System.out.println("Executable version3");
        Function<Executable, Function<Integer, Executable>> fn3 =
                ex -> elem -> {
                    ex.exec();
                    return () -> printInteger.apply(elem);
                };
        Lista<Integer> list = Lista.of(1, 2, 3, 4, 5);
        var exec = list.foldingLeft(neutro, fn3);
        exec.exec();

        //Range recursivo

        System.out.println("Lista recursiva: " + ls.rangeRecursivo(1, 7));
        System.out.println("Lista recursiva: " + ls.range(1, 7));
        System.out.println("Lista tail recursiva:" + ls.rangeTail(1,5,ls));
    }


}
