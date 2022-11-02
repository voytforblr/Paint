
import java.io.*;


public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println(DateWithin30Days(15,25));
    }

    private static Boolean DateWithin30Days(int date1, int date2) {
        if( date2 < date1) { return false; }

        if( date2 >= 5 ) { return false; }
        else { return true; }
    }
}

