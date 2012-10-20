package lib;

import lib.SongModel;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Stas
 * Date: 14.10.12
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class SongModelTest {


    @org.junit.Test
    public void testToByteArray() {
        SongModel m1 =  new SongModel("title","artist","album",2012, (float) 3.14);
        byte[] bytes = m1.toByteArray();
        SongModel m2 = new SongModel(bytes);
        assertEquals(m1.equals(m2),true);
    }
}
