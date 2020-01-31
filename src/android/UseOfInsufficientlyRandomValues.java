package android;

import java.util.Date;
import java.util.Random;

public class UseOfInsufficientlyRandomValues {

    public double unsafe(){
        return Math.random();       /* Bug */
    }

    public int safe(){
        Random random = new Random();           /* Safe */
        random.setSeed(new Date().getTime());
        return (random.nextInt() % 8) + 1;
    }
}
