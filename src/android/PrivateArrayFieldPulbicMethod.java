package android;

public class PrivateArrayFieldPulbicMethod {
    private String[] array;
    public String[] unsafe() {
        return array;      /* Bug */
    }

    public String[] safe(){
        String[] result = null;
        if(this.array != null){
            result = new String[array.length];
            for(int i = 0; i < array.length; i++){
                result[i] = this.array[i];
            }
        }
        return result;
    }
}
