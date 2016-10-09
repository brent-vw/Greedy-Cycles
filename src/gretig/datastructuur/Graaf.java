package gretig.datastructuur;

import java.io.IOException;

/**
 * Created by brent on 10/8/16.
 */
public class Graaf {
    private Boog[] bogen;
    private Top[] toppen;
    private int current;
    private int size;

    public Graaf(int size){
        this.size = size;
        toppen = new Top[size];
        bogen = new Boog[3*size-6];
        current=0;
    }

    public void init_next() throws IOException {
        if(current<size){
            current++;
            toppen[current] = new Top(current);
        } else {
            throw new IOException("Fout ingelezen, top teveel.");
        }
    }

    public void add_boog(int nr) {
        nr = nr-1;
        if(bogen[nr]==null){
            bogen[nr]=new Boog(nr, toppen[current]);
        } else {
            bogen[nr].addBoog(toppen[current]);
        }
    }

    public void graafInfo(){
        System.out.println("Graaf info:");
        for(Top t : toppen){
            System.out.println("Top: "+t.getId());
            System.out.println("Buren: ");
            for(Top t1 : t.getBuren()){
                System.out.print(t1.getId()+" ");
            }
            System.out.println();
        }
    }
}
