package gretig.datastructuur;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by brent on 10/8/16.
 */
public class Top {
    private int id;
    private ArrayList<Top> buren;
    private ArrayList<Integer> burenID;

    public Top(int id){
        this.id = id;
        buren = new ArrayList<>();
        burenID=new ArrayList<>();
    }

    public void addBuur(Top t){
        buren.add(t);
        burenID.add(t.getId());
    }

    public int getId() {
        return id;
    }

    public ArrayList<Top> getBuren(){
        return buren;
    }

    public Collection<Integer> getBurenID() {
        return burenID;
    }
}
