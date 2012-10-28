package lib.mappings;

import java.io.IOException;
import java.io.RandomAccessFile;

import lib.ByteConverter;

/**
 * This class is dispatcher for mappings (pairs <Key, Offset in .db file>).
 * MappingDispatcher saves mappings in addition order. 
 */
public class MappingDispatcher {
	/**
	 * Length of key in bytes
	 */
    public final int KEY_LENGTH = 32;
    
    /**
     * Length of offset in bytes
     */
    public final int OFFSET_LENGTH = Long.SIZE / 8;

    /**
     * File that contains mappings in addition order 
     */
    private RandomAccessFile mappingsFile;
    
    /**
     * Instance of MappingIndexer
     */
    private MappingIndexer mappingIndexer;

    /**
     * Initializing new MappingDispatcher from file
     * @param fileName name of file
     * @throws IOException
     */
    public MappingDispatcher(String fileName) throws IOException {
        mappingsFile = new RandomAccessFile(fileName+".mapping","rw");
        mappingIndexer = new MappingIndexer(fileName+".index");
    }

    /**
     * Inserting new mapping and sorting it by key
     * @param mapping
     * @throws IOException
     */
    public void addMapping(Mapping mapping) throws IOException {
    	if(getMappingsAmount() == 0){
    		//writing first mapping
            mappingIndexer.addIndex(0, 0);
            writeMapping(mapping);
            
            return;
    	}
    	
        long writePosition = mappingsFile.getFilePointer();
        long mappingOffset = 0;

        mappingsFile.seek(0);

        do{
            mappingsFile.seek(mappingIndexer.getIndex(mappingOffset));
            mappingOffset++;
        } while(mappingOffset < getMappingsAmount()
        		&& mapping.getKey().compareTo(readKey()) > 0);

        mappingIndexer.addIndex(mappingOffset-1, writePosition);
        writeMapping(mapping);
    }
    
    /**
     * Retrieving mapping by index
     * @param index offset in array of sorted by key mappings
     * @return mapping
     * @throws IOException
     */
    public Mapping getMapping(long index) throws IOException{
    	mappingsFile.seek(mappingIndexer.getIndex(index)); 
    	
    	return new Mapping(readKey(), mappingsFile.readLong());
    }

    /**
     * Retrieving amount of mappings in mapping file
     * @return mappings amount
     * @throws IOException
     */
    public long getMappingsAmount() throws IOException {
        return (mappingsFile.length() / (KEY_LENGTH + OFFSET_LENGTH));
    }
    
    /**
     * Reading key from current position in mapping file
     * @return
     * @throws IOException
     */
    private String readKey() throws IOException {
        byte[] keyByteRepresentation = new byte[KEY_LENGTH];
        mappingsFile.read(keyByteRepresentation, 0, KEY_LENGTH);

        return ByteConverter.bytesToString(keyByteRepresentation).trim();
    }

    /**
     * Writing new mapping to end of mapping file
     * @param mapping to write
     * @throws IOException
     */
    private void writeMapping(Mapping mapping) throws IOException {
        mappingsFile.seek(mappingsFile.length());//go to end
        
        mappingsFile.write(keyToByteArray(mapping.getKey()), 0, KEY_LENGTH);
        mappingsFile.writeLong(mapping.getOffset());
    }
    
    /**
     * Converts string key to byte array
     * @param key
     * @return byte array representation
     */
    private byte[] keyToByteArray(String key){
        byte[] bytes = new byte[KEY_LENGTH];
        byte[] keyBytes = key.getBytes();
        
        int length = KEY_LENGTH;
            
        if(keyBytes.length < KEY_LENGTH)
            length = keyBytes.length;

        System.arraycopy(keyBytes, 0, bytes, 0, length);

        return bytes;
    }
}
