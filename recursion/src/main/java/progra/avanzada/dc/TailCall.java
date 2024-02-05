package progra.avanzada.dc;

import java.util.function.Supplier;

public interface TailCall<T> {

    T eval();

    TailCall<T> resume();

    boolean inSuspend();

    static <T> TailCall<T> tailReturn(T value) {
        return new Return<>();
    }

    static <T> TailCall<T> tailSuspend(Supplier<TailCall<T>> next) {
        return new Return<>();
    }
}
