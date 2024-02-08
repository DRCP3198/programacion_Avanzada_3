package programa.avanzada.dc;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Lista<T> {

    //por definicion es public static final
    Lista Empty = new Empty();

    T head();

    Lista<T> tail();

    boolean isEmpty();

    static <T> Lista<T> of(T... elements) {

        var tmp = Lista.Empty;
        for (int i = elements.length - 1; i >= 0; i--) {
            tmp = new Const(elements[i], tmp);

        }
        return tmp;

    }

    static <T> Lista<T> of(T head, Lista<T> tail) {
        return new Const<>(head, tail);

    }

    default int count() {
        return 1 + tail().count();


    }

    //    default Lista<T> prepend(T elemnt) {
//        return new Const<>(elemnt, Lista.Empty);
//    }
    default Lista<T> prepend(T elemnt) {
        return Lista.of(elemnt, this);
    }

    default Lista<T> append(T elem) {
        if (this.isEmpty()) {
            return new Const<>(elem, Lista.Empty);
        } else {
            return new Const<>(
                    this.head(),
                    this.tail().append(elem)
            );

        }
    }

    default Lista<T> insert(int index, T element) {

        if (index == 0) {
            return new Const<>(element, this);
        } else {
            return Lista.of(
                    this.head(),
                    this.tail().insert(index - 1, element)
            );

        }

    }

    default T get(int index) {
        if (index == 0) {
            return this.head();

        } else {
            return this.tail().get(index - 1);
        }
    }

    default Lista<T> take(int n) {
        return n <= 0 || this.isEmpty()
                ? Lista.Empty
                : Lista.of(this.head(), this.tail().take(n - 1));

        /*if(n<=0 ||this.isEmpty()){
            return Lista.Empty;
        }else{
            return  Lista.of(this.head(), this.tail().take(n-1));
        }*/
    }

    default Lista<T> drop(int n) {
        return n <= 0 || this.isEmpty()
                ? this
                : this.tail().drop(n - 1);
        /*if(n<=0 || this.isEmpty()){
           return this;
       }else {
           return this.tail().drop(n-1);
       }*/

    }

    default Lista<T> concat(Lista<T> ls) {
        return this.isEmpty()
                ? ls
                : Lista.of(this.head(), this.tail().concat(ls));
        /*if(this.isEmpty()){
           return ls;
       }else {
           return Lista.of(this.head(), this.tail().concat(ls));
       }*/
    }

    default <U> Lista<U> map(Function<T, U> funcion) {
        return this.isEmpty()
                ? Lista.Empty
                : Lista.of(funcion.apply(this.head()), this.tail().map(funcion));
    }

    default <U> U foldingLeft(U identity, Function<U, Function<T, U>> function) {
        //De izquierda a derecha es iterativo
        U ret = identity;

        var tmp = this;
        while (!tmp.isEmpty()) {
            ret = function.apply(ret).apply(tmp.head());
            tmp = tmp.tail();
        }
        return ret;
    }

    default <U> U foldingRight(U identity, Function<T, Function<U, U>> function) {
        U ret = identity;
        var tmp = this.invertFolding(); // Invierte la estructura de datos

        while (!tmp.isEmpty()) {
            ret = function.apply(tmp.head()).apply(ret);
            tmp = tmp.tail();
        }

        return ret;
    }

    default <U> U foldRightHenry(U identify, Function<T, Function<U, U>> fn) {
        if (isEmpty()) {
            return identify;
        } else {
            return fn.apply(head()).apply(tail().foldRightHenry(identify, fn));
        }
    }

    // Método para invertir la estructura de datos

    //Invertir
    default Lista<T> invertFolding() {
        return foldingLeft(Lista.Empty, ls -> t -> ls.prepend(t));
    }

    default <U> Lista<U> mapFolding(Function<T, U> fn) {
        return foldingLeft(Lista.Empty, ls -> t -> ls.prepend(fn.apply(t)));
    }

    default <U> Lista<U> mapFolding2(Function<T, U> fn) {
        return foldingRight(Lista.Empty, t -> ls -> ls.prepend(fn.apply(t)));
    }

    default Integer countFolding() {
        return foldingLeft(0, n -> t -> n + 1);
    }

    default Lista<T> appendFolding(T elem) {
        return foldingRight(Lista.of(elem), t -> ls -> ls.prepend(t));
    }

    default T reduce(T indentity, Function<T, Function<T, T>> fn) {
        return foldingLeft(indentity, u -> t -> fn.apply(u).apply(t));

    }

    //Simplificado
    default T reduce2(Function<T, Function<T, T>> fn) {
        return this.tail().foldingLeft(this.head(), u -> t -> fn.apply(u).apply(t));

    }

    default Lista<T> takeFolding(int n) {
        return foldingLeft(Lista.Empty,
                ls -> t -> ls.count() < n ? ls.append(t) : ls);
    }

    //    default Lista<T> dropFold(int n){
//        int total= this.count()-n;
//        return foldingRight(
//                Lista.Empty,
//                t->ls->ls.count()<total?ls.prepend(t):ls
//        );
//    }
    default Lista<T> dropFold(int n) {
        int tot = this.count() - n;
        return foldingRight(
                Lista.Empty,
                t -> ls -> ls.count() < tot ? ls.prepend(t) : ls);
    }

    default Lista<Integer> rangeRecursivo(Integer vo, Integer vf) {
        if (vo < vf) {
            return Lista.of(vo, rangeRecursivo(vo + 1, vf));
        }
        return Lista.Empty;
    }
    default Lista<Integer> range(Integer vo, Integer vf){
        if(vo >= vf){
            return Lista.Empty;
        }else{
            return Lista.of(vo, range(vo + 1, vf));
        }

    }

    //Range tail Recursivo
    public static Lista<Integer> rangeTail(Integer vo, Integer vf) {
        return rangeTailRecursive(vo, vf, Lista.Empty);
    }

    private static Lista<Integer> rangeTailRecursive(Integer vo, Integer vf, Lista<Integer> acumulador) {
        if (vo >= vf) {
            return acumulador;
        } else {
            return rangeTailRecursive(vo + 1, vf, Lista.of(vo, acumulador));
        }
    }

    //Range unfolding
    default List<Integer> rangeUnfold(int vo, int vf) {
        return IntStream.range(vo, vf)
                .boxed()
                .collect(Collectors.toList());
    }

    //unfold imperactivo
    public static Lista<Integer> unfoldImperative(int start, Function<Integer, Integer> f, Predicate<Integer> predicate) {
        Lista<Integer> result = Lista.Empty;
        int current = start;

        while (predicate.test(current)) {
            result.prepend(current);
            current = f.apply(current);
        }

        return result;
    }
    static <T> Lista<T> unfoldImpe(T start, Function<T, T> fn, Predicate<T> p) {
        Lista<T> result = Lista.Empty;
        T current = start;

        while (p.test(current)) {
            result = result.append(current);
            current = fn.apply(current);
        }

        return result;
    }
    //unfold recursivo
    public static <T> Lista<T> unfoldRecursivo(T start, Function<T, T> fn, Predicate<T> p) {
        if (!p.test(start)) {
            return Lista.Empty;
        } else {
            return Lista.of(start).concat(unfoldRecursivo(fn.apply(start), fn, p));
        }
    }
    //unfold tail recursivo
    public static Lista<Integer> unfoldTailRecursive(int current, Function<Integer, Integer> f, Predicate<Integer> predicate, Lista<Integer> accumulator) {
        if (predicate.test(current)) {
            accumulator.append(current);
            return unfoldTailRecursive(f.apply(current), f, predicate, accumulator);
        } else {
            return accumulator;
        }
    }
}
