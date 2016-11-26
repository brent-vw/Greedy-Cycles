package gretig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by brent on 29/10/16.
 * Logger class voor meting purposes.
 */
public class StatLogger {
    private static String tops=null;
    private static HashMap<String,ArrayList<Long>> costs=null;

    public static void nextLog(String name){
        if(costs!=null) {
            System.out.println("Toppen:"+tops);
            for(String cost : costs.keySet()){
                ArrayList<Long> t = costs.get(cost);
                Collections.sort(t);
                long med=0;
                int middle = t.size()/2;
                if (t.size()%2 == 1) {
                    med = t.get(middle);
                } else {
                    med =(t.get(middle-1)+t.get(middle)) / 2;
                }
                System.out.println(cost+": "+med);
            }
        }
        costs = new HashMap<>();
        tops = name;
    }

    public static void log(String f, long time){
        if(costs.containsKey(f)){
            costs.get(f).add(time);
        } else {
            ArrayList<Long> t = new ArrayList<>();
            t.add(time);
            costs.put(f,t);
        }
    }
}
