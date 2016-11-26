package gretig.datastructuur;

import gretig.StatLogger;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by brent on 10/8/16.
 */
public class Graaf {
    private Boog[] bogen;
    private Top[] toppen;
    private HashSet<Top> toppenlijst;
    private int current;
    private int tops;
    private int boogs;

    public Graaf(int tops, int boogs){
        this.tops = tops;
        this.boogs = boogs;
        toppen = new Top[tops];
        bogen = new Boog[boogs];
        toppenlijst = new HashSet<>(tops);
        current=-1;
    }

    /**
     * Initialiseer de volgende top voor het sequentieel lezen.
     * @throws IOException te veel toppen
     */
    public void init_next() throws IOException {
        if(current<tops){
            current++;
            Top t = new Top(current);
            toppenlijst.add(t);
            toppen[current] = t;
        } else {
            throw new IOException("Fout ingelezen, top teveel.");
        }
    }

    /**
     * Voeg een boog toe aan de huidige top.
     * @param nr boog_id
     */
    public void add_boog(int nr) {
        nr = nr-1;
        if(bogen[nr]==null){
            bogen[nr]=new Boog(nr, toppen[current]);
        } else {
            bogen[nr].addBoog(toppen[current]);
        }
    }

    /**
     * Eerst worden de toppen gesorteerd via pigeonsort (O(n)), daarna worden deze via een stapel verwerkt tot een
     * dominante toppenverzameling gevonden wordt.
     * @return String met de dominante toppenverzameling
     */
    public String dominant(){
        String out="";
        Top[] tt = sortToppen();

        Stack<Top> topstack = new Stack<>();
        for(Top t : tt){
            topstack.push(t);
        }

        HashSet<Top> all = toppenlijst;
        while(!all.isEmpty()&&!topstack.isEmpty()){
            Top t = topstack.pop();
            if(all.contains(t)){
                out+=(t.getId()+1)+" ";

                all.removeAll(t.getBuren());
                all.remove(t);
            }
        }
        return out.trim();
    }

    /**
     * Waring Surpressed gezien enkel maar unchecked toegang binnen array, die telkens veilig bekeken wordt.
     * Sorteer de van meeste buren naar minste buren in O(n+l) via het duivenhokprincipe.
     * n: aantal toppen
     * l: lengte array
     * @return Top[] geordende lijst.
     */
    @SuppressWarnings("unchecked")
    private Top[] sortToppen(){

        int min = toppen[0].getBuren().size();
        int max = toppen[0].getBuren().size();
        for (Top t : toppen){
            int s = t.getBuren().size();
            min = Math.min(min, s);
            max = Math.max(max, s);
        }
        int size = max - min + 1;

        Stack<?>[] pigeon = new Stack<?>[size];

        for (Top t : toppen){
            int s = t.getBuren().size()-min;
            if(pigeon[s]==null){
                pigeon[s]=new Stack<Top>();

            }
            ((Stack<Top>)pigeon[s]).push(t);
        }

        int i = 0;
        Top[] out = new Top[toppen.length];
        for (int count = 0; count < size; count++){
            if(pigeon[count]!=null){
                while(!pigeon[count].empty()){
                    out[i++] = ((Stack<Top>)pigeon[count]).pop();
                }
            }
        }

        return out;
    }

    /**Map van elke top met zijn buren
     * @return toppen gemapt met hun buren
     */
    private HashMap<Top,HashSet<Top>> getTopandNeighbours(){
        HashMap<Top,HashSet<Top>> out = new HashMap<>(toppen.length);
        for(Top t : toppen){
            out.put(t,t.getBuren());
        }

        return out;
    }

    /**
     * Print de graaf naar stdout.
     */
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

    public String hamilton() {
        Top[] t = sortToppen();
        Top[] rev = new Top[t.length];
        boolean found = false;
        for(int i=0;i<t.length;i++){
            rev[i]=t[t.length-i-1];
        }
        String out = "";
        HashSet<Top> added = new HashSet<>(tops);
        Top root = rev[0];
        Top current = root;
        int counter = 0;
        ArrayList<Top> curr_n_l = current.getBuren_l();
        while(added.size()!=tops+1){
            Top candidate = curr_n_l.get(counter);
            if(added.contains(candidate)){
                if(candidate.equals(root)&&added.size()==tops){
                    found=true;
                    break;
                } else if(++counter>=curr_n_l.size()){
                    found=false;
                    break;
                }
            } else {
                added.add(candidate);
                counter=0;
                current = candidate;
                curr_n_l = current.getBuren_l();
                out+=(current.getId()+1)+" ";
            }
        }

        if(found){
            return out.trim();
        } else {
            return "geen cykel gevonden";
        }
    }

}
