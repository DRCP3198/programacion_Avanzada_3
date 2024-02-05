package progra.avanzada.dc;

import java.util.function.Supplier;

final class Suspend<T> implements TailCall<T> {

    private Supplier<TailCall<T>> next;
    Suspend(Supplier<TailCall<T>> next){
        this.next= next;

    }

    @Override
    public T eval() {
        TailCall<T> tmp= this;
        while (tmp.inSuspend()){
            tmp= tmp.resume();
        }
        return tmp.eval();
    }

    @Override
    public TailCall<T> resume() {
        return next.get();
    }

    @Override
    public boolean inSuspend() {
        return true;
    }

    @Override
    public String toString() {
        return "Suspend{" +
                "next=" + next +
                '}';
    }
}