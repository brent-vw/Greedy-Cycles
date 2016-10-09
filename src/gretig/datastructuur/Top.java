package gretig.datastructuur;

import java.util.ArrayList;

/**
 * Created by brent on 10/8/16.
 */
public class Top {
    private int id;
    private ArrayList<Top> buren;

    public Top(int id){
        this.id = id;
        buren = new ArrayList<>();
    }

    public void addBuur(Top t){
        buren.add(t);
    }

    public int getId() {
        return id;
    }

    public ArrayList<Top> getBuren(){
        return buren;
    }
}
