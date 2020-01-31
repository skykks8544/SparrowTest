package android;

public class UncontrolledRecursion {

    public int unsafe(int n){
        return n * unsafe(n - 1);   /* Bug */
    }

    public int safe(int n){
        int i;
        if(n == 1){
            i = 1;
        } else {
            i = n * safe(n - 1);    /* Safe */
        }
        return i;
    }
}
