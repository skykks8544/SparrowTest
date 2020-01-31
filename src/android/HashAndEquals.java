package android;

public class HashAndEquals {

    public boolean equals(Object object){               /* Bug */
        if(object == null){
            return false;
        }
        return this.hashCode() == object.hashCode();
    }
}
