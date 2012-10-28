package tests.mappings;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import lib.mappings.*;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

public class MappingDispatcherTest {
	private MappingDispatcher mappingDispatcher;

	@Before
	public void prepareFile() throws IOException{
		(new File("tests/dispatcher.mapping")).delete(); //remove old dispatcher
		(new File("tests/dispatcher.index")).delete(); //remove old indexer
		
        mappingDispatcher = new MappingDispatcher("tests/dispatcher");
        
        mappingDispatcher.addMapping(new Mapping("Unforgiven", 0));
        mappingDispatcher.addMapping(new Mapping("Fade to black", 1));
        mappingDispatcher.addMapping(new Mapping("Nothing else matters", 2));
        mappingDispatcher.addMapping(new Mapping("Zet index", 3));
	}
	
    @Test
    public void testSorting() throws Exception {
        assertEquals("Fade to black", mappingDispatcher.getMapping(0).getKey());
        assertEquals("Nothing else matters", mappingDispatcher.getMapping(1).getKey());
        assertEquals("Unforgiven", mappingDispatcher.getMapping(2).getKey());
        assertEquals("Zet index", mappingDispatcher.getMapping(3).getKey());
        
        assertEquals(4, mappingDispatcher.getMappingsAmount());
    }
    
    @Test
    public void testSearch() throws IOException{
    	assertEquals(0, mappingDispatcher.getObjectOffset("Unforgiven"));
    	assertEquals(1, mappingDispatcher.getObjectOffset("Fade to black"));
    	assertEquals(2, mappingDispatcher.getObjectOffset("Nothing else matters"));
    	
    	boolean catched = false;
    	
    	try{
    		mappingDispatcher.getObjectOffset("Some fake key");
    	} catch (NoSuchElementException e){
    		catched = true;
    	} finally{
    		assertTrue(catched);
    	}
    }
}
