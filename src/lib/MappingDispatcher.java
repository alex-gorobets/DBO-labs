package lib;

import java.io.IOException;
import java.io.RandomAccessFile;

public class MappingDispatcher {
    public final int KEY_LENGTH = 16;
    public final int OFFSET_LENGTH = 8;

    private RandomAccessFile mapping;
    private MappingIndexer indexer;

    public MappingDispatcher(String fileName) throws IOException {
        mapping = new RandomAccessFile(fileName+".mapping","rw");
        indexer = new MappingIndexer(fileName+".index");
    }

    public void addMapping(String key, long offset) throws IOException {
        System.out.println("adding mapping: " + key + " " + offset);
        long writePosition = mapping.getFilePointer();
        mapping.seek(0);
        long mappingOffset = 0;


        System.out.println("Mapping file:");
        do{
            mapping.seek(indexer.getOffset(mappingOffset));
            System.out.println("\t" + getKey());
            mappingOffset++;
        } while(mappingOffset < this.getLength() && key.compareTo(getKey()) > 0);

        indexer.set(mappingOffset-1, writePosition);
        this.write(key, offset);
    }

    public long getLength() throws IOException {
        return (mapping.length() / (KEY_LENGTH + OFFSET_LENGTH));
    }
    
    public Mapping getMapping(long i) throws IOException{
    	mapping.seek(indexer.getOffset(i));
    	
    	Mapping m = new Mapping();
    	m.key = getKey();
    	m.offset = mapping.readLong();
    	
    	return m;
    }

    private String getKey() throws IOException {
        byte[] keyArr = new byte[KEY_LENGTH];
        mapping.read(keyArr, 0, KEY_LENGTH);

        return ByteConverter.bytesToString(keyArr);
    }

    private void write(String key, long offset) throws IOException {
        mapping.seek(mapping.length());
        mapping.write(prepareKey(key), 0, KEY_LENGTH);
        mapping.writeLong(offset);
    }
        
    private byte[] prepareKey(String key){
        byte[] bytes = new byte[KEY_LENGTH];
        byte[] keyBytes = key.getBytes();
        int length = KEY_LENGTH;
            
        if(keyBytes.length < KEY_LENGTH)
            length = keyBytes.length;

        System.arraycopy(keyBytes, 0, bytes, 0, length);

        return bytes;
    }
}
