package tests;

import lib.Model;
import lib.SongModel;
import lib.repositories.FileRepository;
import lib.repositories.Repository;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

public class FileRepositoryTest {

    Repository fileRepository;

    public FileRepositoryTest() throws IOException {
        fileRepository = new FileRepository("test");
    }

    @Test
    public void testGetAll() throws Exception {
        Model[] songModels = new SongModel[3];
        songModels[0] = new SongModel("FirstTitle","FirstArtist","FirstAlbum",1998,(float)11.2);
        songModels[1] = new SongModel("SecondTitle","SecondArtist","SecondAlbum",1998,(float)11.2);
        songModels[2] = new SongModel("ThirdTitle","ThirdArtist","ThirdAlbum",1998,(float)11.2);

        fileRepository.add(new SongModel("FirstTitle","FirstArtist","FirstAlbum",1998,(float)11.2));
        fileRepository.add(new SongModel("SecondTitle","SecondArtist","SecondAlbum",1998,(float)11.2));
        fileRepository.add(new SongModel("ThirdTitle","ThirdArtist","ThirdAlbum",1998,(float)11.2));

        assertArrayEquals(songModels,fileRepository.get());

        fileRepository.remove("FirstTitle");
        fileRepository.remove("SecondTitle");
        fileRepository.remove("ThirdTitle");
    }      
    
    @Test
    public void testGetByKey() throws IOException{
        fileRepository.add(new SongModel("FirstTitle","FirstArtist","FirstAlbum",1998,(float)11.2));
        fileRepository.add(new SongModel("SecondTitle","SecondArtist","SecondAlbum",1998,(float)11.2));

        Model m1 = new SongModel("SecondTitle","SecondArtist","SecondAlbum",1998,(float)11.2);       
        assertEquals(m1,fileRepository.get("SecondTitle"));

        fileRepository.remove("FirstTitle");
        fileRepository.remove("SecondTitle");
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
        assertNotNull(fileRepository.get("FirstTitle"));
        assertNotNull(fileRepository.get("ThirdTitle"));

        fileRepository.remove("FirstTitle");
        fileRepository.remove("ThirdTitle");
        assertEquals(0,fileRepository.size());
    }

    @Test
    public void testSet() throws IOException{
        fileRepository.add(new SongModel("FirstTitle","FirstArtist","FirstAlbum",1998,(float)11.2));
        fileRepository.add(new SongModel("SecondTitle","SecondArtist","SecondAlbum",1998,(float)11.2));

        Model m1 = new SongModel("ThirdTitle","ThirdArtist","ThirdAlbum",1998,(float)11.2);
        fileRepository.set("SecondTitle",m1);
        assertEquals(m1,fileRepository.get(1,1));

        fileRepository.remove("FirstTitle");
        fileRepository.remove("ThirdTitle");
    }

    @Test
    public void testAdd() throws Exception {
        fileRepository.add(new SongModel("FirstTitle","FirstArtist","FirstAlbum",1998,(float)11.2));
        assertNotNull(fileRepository.get("FirstTitle"));

        fileRepository.remove("FirstTitle");
    }
}
