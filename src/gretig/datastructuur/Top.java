package gretig.datastructuur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by brent on 10/8/16.
 */
public class Top {
    private int id;
    private HashSet<Top> buren;
    private ArrayList<Top> buren_l;

    public Top(int id){
        this.id = id;
        buren = new HashSet<>();
        buren_l = new ArrayList<>();
    }

    /** Callback wanneer een boog een nieuwe buur vindt.
     * @param t: buurman
     */
    public void addBuur(Top t){
        buren.add(t);
        buren_l.add(t);
    }

    /**
     * @return id van de top startend bij 0.
     */
    public int getId() {
        return id;
    }

    public HashSet<Top> getBuren(){
        return buren;
    }

    public ArrayList<Top> getBuren_l(){
        return buren_l;
    }
}
