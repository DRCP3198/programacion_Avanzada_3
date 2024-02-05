package progra.avanzada.dc;

public class Return <T> implements TailCall<T> {
    @Override
    public T eval() {
        return null;
    }

    @Override
    public TailCall<T> resume() {
        return null;
    }

    @Override
    public boolean inSuspend() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
