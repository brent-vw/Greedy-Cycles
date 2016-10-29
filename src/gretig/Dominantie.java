package gretig;

import gretig.datastructuur.Graaf;
import gretig.datastructuur.GraafDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by brent on 10/7/16.
 */
public class Dominantie {
    public static void main(String[] args) {
        String[] tests = {"graaf1.sec","graaf5.sec","graaf8.sec"};
        for (String t : tests) {
            try (InputStream is = Dominantie.class.getResourceAsStream(t)) {
                System.out.println(t);
                int b;
                String header = "";
                int i = 0;
                while ((b = is.read()) != -1) {
                    header += ((char) b);
                    if (header.equals(">>SEC<<")) {
                        break;
                    } else if (header.length() > 7) {
                        throw new IOException("Wrong Header");
                    }
                }
                GraafDecoder g = new GraafDecoder(is);
                ArrayList<Graaf> gfs = g.decodeGrafen();
                long avg = 0;
                System.nanoTime();
                for (Graaf gf : gfs) {
                    long time = System.nanoTime();
                    String k = gf.dominant();
                    long duration = System.nanoTime() - time;
                    if (avg == 0)
                        avg = duration;
                    avg = (avg + duration) / 2;
                   System.out.println(k + ":\tduration:" + duration);
                }
                System.out.println("Average duration:" + avg);
            } catch (IOException e) {
                System.err.println("IOEX");
            }
        }
    }
}
