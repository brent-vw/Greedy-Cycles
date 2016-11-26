package gretig.datastructuur;

import gretig.datastructuur.Graaf;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by brent on 10/8/16.
 */
public class GraafDecoder {
    private InputStream is;
    private boolean done;
    private int bytesize;

    public GraafDecoder(InputStream is) throws IOException {
        this.is = is;
        done = false;
    }

    /** Decodeer de grafen één voor één.
     * @return gf lijst van alle grafen
     * @throws IOException fout bij het lezen
     */
    public ArrayList<Graaf> decodeGrafen() throws IOException {
        ArrayList<Graaf> gf = new ArrayList<>();
        while(!done){
            this.bytesize = is.read();
            int toppen = readNumber();
            int bogen = readNumber();
            if(bytesize==-1||toppen==-1||bogen==-1) break;
            gf.add(decodeGraaf(toppen, bogen));
        }
        return gf;
    }

    /**
     * Lees het volgende getal in volgens de bytesize.
     * @return answer: ByteDecoded number
     * @throws IOException fout bij lezen
     */
    public int readNumber() throws IOException {
        int answer = 0;
        for (int i=0; i<bytesize;i++){
            int b=is.read();
            if(b==-1){
                return -1;
            }
            answer+=b<<(8*i);
        }
        return answer;
    }

    /** Verwerk 1 graaf.
     * @param toppen: aantal toppen
     * @param bogen: aantal bogen
     * @return g: de volledige graaf
     * @throws IOException
     */
    public Graaf decodeGraaf(int toppen, int bogen) throws IOException {
        int b;
        int teller=0;
        Graaf g = new Graaf(toppen, bogen);
        g.init_next();
        while((b=readNumber())!=-1){
            if(b==0){
                teller++;
                if(teller>=toppen) break;
                g.init_next();
            } else {
                g.add_boog(b);
            }
        }
        if (b==-1)
            done=true;
        return g;
    }
}
