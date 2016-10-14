package gretig;

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

    public GraafDecoder(InputStream is) {
        this.is = is;
        done = false;
    }

    public ArrayList<Graaf> decodeGrafen() throws IOException {
        int b;
        ArrayList<Graaf> gf = new ArrayList<>();
        while(!done){
            int bytesize = is.read();
            int toppen = is.read();
            if(bytesize==-1||toppen==-1) break;
            gf.add(decodeGraaf(bytesize, toppen));
        }
        return gf;
    }

    public Graaf decodeGraaf(int bytesize, int toppen) throws IOException {
        int b;
        int teller=0;
        int byteteller=0;
        int curval=0;
        Graaf g = new Graaf(toppen);
        g.init_next();
        while(((b=is.read())!=-1)){
            if(b==0) {
                teller++;
                if(teller>=toppen) break;
                g.init_next();

            }
            else if(byteteller<bytesize){
                curval+=b<<(8*byteteller);
                byteteller++;
            } else {
                g.add_boog(curval);
                curval=0;
                byteteller=0;
            }
        }
        if (b==-1)
            done=true;
        return g;
    }
}
