package gretig;

import gretig.datastructuur.Graaf;
import gretig.datastructuur.GraafDecoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by brent on 10/7/16.
 */
public class Hamilton {
    public static void main(String[] args) {
        try (InputStream is = System.in) {
            int b;
            String header = "";
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
            for (Graaf gf : gfs) {
                String k = gf.hamilton();
                System.out.println(k);
            }
        } catch (IOException e) {
            System.err.println("IOEX");
        }
    }
    }
