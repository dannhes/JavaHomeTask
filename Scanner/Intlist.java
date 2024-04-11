package Scanner;

import java.util.Arrays;

public class Intlist {
    private int[] intList = new int[2];
    private int ind = 1;
    public Intlist() {
        this.intList = new int[2];
        this.ind = 1;
    }
    public void countplus() {
        intList[0]++;
    }
    public int len() {
        return ind;
    }
    public void addtoL(int a) {
        intList[ind] = a;
        ind+=1;
        if (this.intList.length == this.ind) {
            this.intList = Arrays.copyOf(this.intList,ind*2);
        }
    }
    public int get(int index) {
        return intList[index];
    }
}
