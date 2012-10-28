package tests.mappings;

import java.io.File;

import lib.mappings.*;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;

public class MappingDispatcherTest {
    @Test
    public void testSorting() throws Exception {
		(new File("tests/dispatcher.mapping")).delete(); //remove old dispatcher
		(new File("tests/dispatcher.index")).delete(); //remove old indexer
		
        MappingDispatcher mappingDispatcher = new MappingDispatcher("tests/dispatcher");
        
        mappingDispatcher.addMapping(new Mapping("Unforgiven", 0));
        mappingDispatcher.addMapping(new Mapping("Fade to black", 1));
        mappingDispatcher.addMapping(new Mapping("Nothing else matters", 2));

        assertEquals("Fade to black", mappingDispatcher.getMapping(0).getKey());
        assertEquals("Nothing else matters", mappingDispatcher.getMapping(1).getKey());
        assertEquals("Unforgiven", mappingDispatcher.getMapping(2).getKey());
        
        assertEquals(3, mappingDispatcher.getMappingsAmount());
    }
}
