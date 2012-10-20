package tests;

import lib.MappingDispatcher;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Stas
 * Date: 14.10.12
 * Time: 20:51
 * To change this template use File | Settings | File Templates.
 */
public class MappingDispatcherTest {
    @Test
    public void testAddMapping() throws Exception {
        String str1 = "Nothing";
        String str2 = "Fade";

        System.out.println("compare: " +str1.compareTo(str2));
        
        
        MappingDispatcher d = new MappingDispatcher("C:/test/test");
        d.addMapping("Unforgiven", 0);
        d.addMapping("Fade to black", 1);
        d.addMapping("Nothing else matters", 2);

        //assertEquals(true, false);
    }
}
