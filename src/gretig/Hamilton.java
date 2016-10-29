package gretig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by brent on 10/7/16.
 */
public class Hamilton {
    public static void main(String[] args) {
        try (InputStream is = Dominantie.class.getResourceAsStream("alle_8.sec")) {
            int b;
            String header = "";
            int i = 0;
            int j = 0;
            while ((b = is.read()) != -1) {
                System.out.print(b+"\t");
                i++;
                j++;
                if (i==60){
                    i=0;
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
