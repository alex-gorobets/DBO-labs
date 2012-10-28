package tests;

import org.junit.Test;

import lib.SongModel;

import static junit.framework.Assert.assertEquals;

public class SongModelTest {


    @Test
    public void testToByteArray() {
        SongModel m1 =  new SongModel("title","artist","album",2012, (float) 3.14);
        byte[] bytes = m1.toByteArray();
        SongModel m2 = new SongModel(bytes);
        assertEquals(m1.equals(m2),true);
    }
    
    @Test
    public void testEquals(){
    	SongModel m1 = new SongModel("BBB SecondTitle","SecondArtist","SecondAlbum",1998,(float)11.2);
    	SongModel m2 = new SongModel("BBB SecondTitle","SecondArtist","SecondAlbum",1998,(float)11.2);
    	
    	assertEquals(m1, m2);
    }
}
