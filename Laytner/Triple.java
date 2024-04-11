package Laytner;

public class Triple<A, B, C> {
    private A first;
    private B second;
    private C third;

    public Triple(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public C getThird() {
        return third;
    }

    public void replaceFirst(A change){
        first = change;
    }

    public void replaceSecond(B change){
        second = change;
    }

    public void replaceTheard(C change){
        third = change;
    }



}
