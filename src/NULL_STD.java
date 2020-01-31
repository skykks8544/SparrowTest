import java.io.File;

public final class NULL_STD {

    public static final void main(String[] ar) {
        System.out.println(new File("/").getParent().length());
        System.out.println(new File("c:\\").getParent().length());
    }

}
