package gretig.datastructuur;

/**
 * Created by brent on 10/8/16.
 */
public class Boog {
    private int id;
    private Top t1;
    private Top t2;

    public Boog(int id, Top t1){
        this.id=id;
        this.t1 = t1;
    }

    /**Notify beide partijen dat ze er een buur bijhebben.
     * @param t2: buurman
     */
    public void addBoog(Top t2){
        this.t2=t2;
        t1.addBuur(t2);
        t2.addBuur(t1);
    }
}
