package tests;

import gretig.datastructuur.Top;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by brent on 29/10/2016.
 */
public class TopTest {
    @Test
    public void addBuur() throws Exception {
        Top t1 = new Top(1);
        Top t2 = new Top(2);
        Top t3 = new Top(3);
        Top[] ts = {t2,t3};
        t1.addBuur(t2);
        t1.addBuur(t3);
        assertTrue(t1.getBuren().containsAll(Arrays.asList(ts)));
    }

    @Test
    public void getId() throws Exception {
        Top t1 = new Top(1);
        assertTrue(t1.getId()==1);

    }

    @Test
    public void getBuren() throws Exception {
        addBuur();
    }

    @Test
    public void getBurenID() throws Exception {
        Top t1 = new Top(1);
        Top t2 = new Top(2);
        Top t3 = new Top(3);
        Integer[] ts = {t2.getId(),t3.getId()};
        t1.addBuur(t2);
        t1.addBuur(t3);
        assertArrayEquals(t1.getBurenID().toArray(),ts);
    }

}