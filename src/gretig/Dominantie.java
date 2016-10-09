package gretig;

import gretig.datastructuur.Graaf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Created by brent on 10/7/16.
 */
public class Dominantie {
    public static void main(String[] args) {
        try (InputStream is = System.in) {
            int b;
            int bytesize=0;
            String header="";
            while ((b = is.read()) != -1) {
                header += ((char) b);
                if(!header.equals(">>SEC<<")&&header.length()<7) {
		//TODO
		} else if(header.length()>7) {
                    throw new IOException("Wrong Header");
                } else {
                    break;
                }
            }
            GraafDecoder g = new GraafDecoder(is);
            for(Graaf gr : g.decodeGrafen()){
                gr.graafInfo();
            }
        } catch (IOException e) {
            System.err.println("IOEX");
        }
    }
}
