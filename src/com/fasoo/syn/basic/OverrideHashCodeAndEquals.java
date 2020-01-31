package com.fasoo.syn.basic;

import java.util.HashMap;
import java.util.Map;

public class OverrideHashCodeAndEquals {
    public static void main(String[] args) {
        Map<CreditCard, String> m = new HashMap<CreditCard, String>();
        m.put(new CreditCard(100), "4111111111111111");
        System.out.println(m.get(new CreditCard(100)));

        Map<CreditCardSafe, String> m1 = new HashMap<CreditCardSafe, String>();
        m1.put(new CreditCardSafe(100), "4111111111111111");
        System.out.println(m.get(new CreditCard(100)));
    }
}

final class CreditCard {
    private final int number;

    public CreditCard(int number) {
        this.number = number;
    }

    public boolean equals(Object o) { /* Bug */
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreditCard)) {
            return false;
        }
        CreditCard cc = (CreditCard) o;
        return cc.number == number;
    }

}

final class CreditCardSafe {
    private final int number;

    public CreditCardSafe(int number) {
        this.number = number;
    }

    public boolean equals(Object o) { /* Safe */
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreditCardSafe)) {
            return false;
        }
        CreditCardSafe cc = (CreditCardSafe) o;
        return cc.number == number;
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + number;
        return result;
    }

}