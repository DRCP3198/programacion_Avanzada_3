package progra.avanzada.dc.recursion;

import progra.avanzada.dc.TailCall;

import static progra.avanzada.dc.TailCall.tailReturn;
import static progra.avanzada.dc.TailCall.tailSuspend;

public class TestPrincialRecursion {

    static Integer sumaOriginal(int x, Integer y){
        return  y==0
                ?x
                :sumaOriginal(x+1,y-1);
    }
    static TailCall<Integer> sumaRecusiva(int x, Integer y){
        return  y==0
                ?tailReturn(x)
                :tailSuspend(()->sumaRecusiva(x+1,y-1));
    }

    public static void main(String[] args) {

        var n3 = tailSuspend(()->
        tailSuspend(()->
        tailReturn(10)));
        System.out.println(n3.eval());

        var ret = sumaRecusiva(3,5);
        System.out.println(ret);
        var suma= ret.eval();
        System.out.println(suma);
    }
}
