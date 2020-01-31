package android;

public class MissuingCheckNullParameter {

    public boolean unsafe(Object object){
        return toString().equals(object.toString());    /* Bug */
    }

    public boolean safe(Object object){
        if(object != null){
            return toString().equals(object.toString());    /* Safe */
        } else {
            return false;
        }
    }
}
