package tests;

import lib.SongModel;
import lib.repositories.FileRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertArrayEquals;

public class FileRepositoryTest {

    FileRepository fileRepository;

    @Before
    public void init() throws IOException{
		(new File("tests/repository")).delete(); //remove old repository
		(new File("tests/repository.mapping")).delete(); //remove old dispatcher
		(new File("tests/repository.index")).delete(); //remove old indexer
		
        fileRepository = new FileRepository("tests/repository");
    }

    @Test
    public void testGetAll() throws Exception {
    	SongModel[] songModels = new SongModel[3];
        songModels[0] = new SongModel("AAA FirstTitle","FirstArtist","FirstAlbum",1998,(float)11.2);
        songModels[1] = new SongModel("BBB SecondTitle","SecondArtist","SecondAlbum",1998,(float)11.2);
        songModels[2] = new SongModel("CCC ThirdTitle","ThirdArtist","ThirdAlbum",1998,(float)11.2);

        fileRepository.add(songModels[1]);
        fileRepository.add(songModels[0]);
        fileRepository.add(songModels[2]);
        
        System.out.println(Arrays.toString(fileRepository.get()));

        assertArrayEquals(songModels,fileRepository.get());
    }      
    
    @Test
    public void testGetByKey() throws IOException{
        fileRepository.add(new SongModel("FirstTitle","FirstArtist","FirstAlbum",1998,(float)11.2));
        fileRepository.add(new SongModel("SecondTitle","SecondArtist","SecondAlbum",1998,(float)11.2));

        SongModel m1 = new SongModel("SecondTitle","SecondArtist","SecondAlbum",1998,(float)11.2);       
        assertEquals(m1,fileRepository.get("SecondTitle"));
    }

    @Test
    public void testRemove() throws IOException {
        assertEquals(0,fileRepository.size());
        fileRepository.add(new SongModel("FirstTitle","FirstArtist","FirstAlbum",1998,(float)11.2));
        fileRepository.add(new SongModel("SecondTitle","SecondArtist","SecondAlbum",1998,(float)11.2));
        fileRepository.add(new SongModel("ThirdTitle","ThirdArtist","ThirdAlbum",1998,(float)11.2));
        assertEquals(3,fileRepository.size());

        fileRepository.remove("SecondTitle");

        assertEquals(null,fileRepository.get("SecondTitle"));
        assertEquals("FirstTitle", fileRepository.get("FirstTitle").getKey());
        assertEquals("ThirdTitle", fileRepository.get("ThirdTitle").getKey());
        

        fileRepository.remove("FirstTitle");
        fileRepository.remove("ThirdTitle");
        assertEquals(0,fileRepository.size());
    }

    @Test
    public void testSet() throws IOException{
        fileRepository.add(new SongModel("key1","FirstArtist","FirstAlbum",1998,(float)11.2));
        assertEquals("FirstArtist", fileRepository.get("key1").getArtist());
        
        fileRepository.set(new SongModel("key1","AnotherArtist","FirstAlbum",1998,(float)11.2));
        assertEquals("AnotherArtist", fileRepository.get("key1").getArtist());
        
        fileRepository.set(new SongModel("key2","SecondArtist","FirstAlbum",1998,(float)11.2));
        assertEquals("SecondArtist", fileRepository.get("key2").getArtist());
        assertEquals("AnotherArtist", fileRepository.get("key1").getArtist());
    }

    @Test
    public void testAdd() throws Exception {
        fileRepository.add(new SongModel("FirstTitle","FirstArtist","FirstAlbum",1998,(float)11.2));
        assertEquals("FirstTitle", fileRepository.get("FirstTitle").getKey());
    }
    
    @Test
    public void testGetWithLimits(){
    	
    }
    
    @After
    public void closeFiles() throws IOException{
    	fileRepository.close();
    }
}
