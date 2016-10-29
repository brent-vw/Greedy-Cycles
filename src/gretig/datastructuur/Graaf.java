package gretig.datastructuur;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by brent on 10/8/16.
 */
public class Graaf {
    private Boog[] bogen;
    private Top[] toppen;
    private ArrayList<Top> toppenlijst;
    private int current;
    private int tops;
    private int boogs;

    public Graaf(int tops, int boogs){
        this.tops = tops;
        this.boogs = boogs;
        toppen = new Top[tops];
        bogen = new Boog[boogs];
        toppenlijst = new ArrayList<>(tops);
        current=-1;
    }

    public void init_next() throws IOException {
        if(current<tops){
            current++;
            Top t = new Top(current);
            toppenlijst.add(current, t);
            toppen[current] = t;
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

    public String dominant(){
        String out="";
        Collections.sort(toppenlijst,(o1, o2) -> Integer.compare(o2.getBuren().size(), o1.getBuren().size()));
        ArrayList<Top> t = toppenlijst;
        HashSet<Top> covered = new HashSet<>(toppenlijst.size());
        int teller=0;
        for(int i = 0; i<toppen.length; i++){
            Top b = toppen[i];
            if(!covered.contains(b)){
                covered.addAll(b.getBuren());
                out+=(b.getId()+1)+" ";
            }
        }
        /*while (t.size()>0){
            Top big = t.remove(0);
            t.removeAll(big.getBuren());
            teller++;
            out+=(big.getId()+1)+" ";
        }*/
        return out.trim();
    }

    public void graafInfo(){
        System.out.println("Graaf info:");
        for(Top t : toppen){
            System.out.println("Top: "+(t.getId()+1));
            System.out.println("Buren: ");
            for(Top t1 : t.getBuren()){
                System.out.print((t1.getId()+1)+" ");
            }
            System.out.println();
        }
    }
}
