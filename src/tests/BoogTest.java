package tests;

import gretig.datastructuur.Boog;
import gretig.datastructuur.Top;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by brent on 29/10/2016.
 */
public class BoogTest {
    @Test
    public void addBoog() throws Exception {
        Top a = new Top(1);
        Top b = new Top(2);
        Boog c = new Boog(1,a);
        c.addBoog(b);
        Top[] abuur = {b};
        Top[] bbuur = {a};
        assertArrayEquals(a.getBuren().toArray(),abuur);
        assertArrayEquals(b.getBuren().toArray(), bbuur);
    }

}