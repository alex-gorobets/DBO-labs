package lib.repositories;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.NoSuchElementException;

import lib.SongModel;
import lib.mappings.Mapping;
import lib.mappings.MappingDispatcher;

/**
 * This class implements Repository interface by saving data in file database.<br/>
 * There are 3 type of files used by this repository:<br/>
 * <ul>
 *     <li><b>database file</b> (.db) - contains only Objects in their byte representation</li>
 *     <li><b>mapping file</b> (.mapping) - contains Mappings (pair <Key, Offset in .db file>) in insertion order</li>
 *     <li><b>indexes file</b> (.index) - contains sorted by Key offsets of Mappings</li>
 * </ul>
 */

public class FileRepository implements Repository {
    private RandomAccessFile db;
    private MappingDispatcher mappingDispatcher;

    /**
     * Creates new database in specified file
     * @param fileName file path
     * @throws IOException
     */
    public FileRepository(String fileName) throws IOException {
        db = new RandomAccessFile(fileName,"rw");
        mappingDispatcher = new MappingDispatcher(fileName);
        
    }
    @Override
    public long size() throws IOException {
        return mappingDispatcher.getMappingsAmount();
    }

    /**
     * Returns all objects in database sorted by keys
     * @return Model[]
     * @throws IOException 
     */
    @Override
    public SongModel[] get() throws IOException {
    	SongModel[] objects = new SongModel[(int) size()];
    	
    	for(int i = 0; i < size(); i++){
        	db.seek(mappingDispatcher.getMapping(i).getOffset());
        	byte[] out = new byte[SongModel.SERIALIZED_LENGTH];
        	
        	db.readFully(out);
        	objects[i] = new SongModel(out);
    	}
    	
        return objects;
    }

    /**
     * Returns objects starting with offset. Maximal amount of objects = limit. Object sorted by keys
     * @param limit maximal amount of objects
     * @param offset starting index (from 0)
     * @return Model[]
     * @throws IOException 
     */
    @Override
    public SongModel[] get(int limit, int offset) throws IOException {
    	int length = (int) size() < limit ? (int) size() : limit;
    	SongModel[] objects = new SongModel[length];
    	
    	for(int i = offset, j = 0; i < size() && j < limit; i++, j++){
        	db.seek(mappingDispatcher.getMapping(i).getOffset());
        	byte[] out = new byte[SongModel.SERIALIZED_LENGTH];
        	
        	db.readFully(out);
        	objects[i] = new SongModel(out);
    	}
    	
        return objects;
    }

    /**
     * Returns object with specified key. If object doesn't exists it returns null-pointer
     * @param key unique key of object
     * @return Model
     * @throws IOException
     */
    @Override
    public SongModel get(String key) throws IOException {
    	long offset;
    	
    	try{
    		offset = mappingDispatcher.getObjectOffset(key);
    	} catch (NoSuchElementException e){
    		return null;
    	}
    	
    	db.seek(offset);
    	byte[] out = new byte[SongModel.SERIALIZED_LENGTH];
    	
    	db.readFully(out);
    	return new SongModel(out);
    }

    @Override
    public void add(SongModel model) throws IOException {
    	db.seek(db.length());
    	mappingDispatcher.addMapping(new Mapping(model.getKey(), db.getFilePointer()));
        db.write(model.toByteArray());
    }

    @Override
    public void set(SongModel model) throws IOException {
    	long offset;
    	
    	try{
    		offset = mappingDispatcher.getObjectOffset(model.getKey());
    	} catch (NoSuchElementException e){
    		add(model);
    		return;
    	}
    	
    	db.seek(offset);
    	db.write(model.toByteArray());
    }

    @Override
    public void remove(String key) throws IOException {
    	mappingDispatcher.removeMapping(key);
    }
    
    public void close() throws IOException{
    	db.close();
    	mappingDispatcher.close();
    }
}
