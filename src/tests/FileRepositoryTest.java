package tests;

import lib.FileRepository;
import lib.Repository;
import lib.SongModel;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;

public class FileRepositoryTest {

    Repository fileRepository;

    public FileRepositoryTest() throws IOException {
        fileRepository = new FileRepository("test");
    }

    @Test
    public void testGet() throws Exception {

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
    public void testAdd() throws Exception {

    }
}
