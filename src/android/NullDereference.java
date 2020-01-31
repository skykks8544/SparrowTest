package android;

public class NullDereference {

    public String ret3() {
        String x = null;
        String y = "asdf";
        return x;
    }

    public String ret2() {
        return ret3();
    }

    public String ret1() {
        return ret2();
    }

    void trig() {
        String x = ret1();
        String y = "zxcv";
        x.toString();           /* BUG */

        ((String) ((Object) ret1())).toUpperCase(); /* BUG */
    }
}
