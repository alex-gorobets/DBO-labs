package lib;

import java.io.IOException;
import java.io.RandomAccessFile;

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

//!TODO implement generic types

public class FileRepository implements Repository {
    private RandomAccessFile db;
    private MappingDispatcher md;

    /**
     * Creates new database in specified file
     * @param fileName file path
     * @throws IOException
     */
    public FileRepository(String fileName) throws IOException {
        db = new RandomAccessFile(fileName,"rw");
        md = new MappingDispatcher(fileName);
        
    }
    @Override
    public long size() {
        return 0;
    }

    /**
     * Returns all objects in database
     * @return Model[]
     */
    @Override
    public Model[] get() {
        return new SongModel[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Returns objects starting with offset. Maximal amount of objects = limit
     * @param limit maximal amount of objects
     * @param offset starting index (from 0)
     * @return Model[]
     */
    @Override
    public Model[] get(int limit, int offset) {
        return new SongModel[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Returns object with specified key. If object doesn't exists it returns null-pointer
     * @param key unique key of object
     * @return Model
     * @throws IOException
     */
    @Override
    public Model get(String key) throws IOException {
    	//!TODO remove hard coding
    	db.seek(allocateKey(key));
    	byte[] out = new byte[56];
    	
    	db.readFully(out);
    	return new SongModel(out);
    }

    @Override
    public void add(Model model) throws IOException {
    	md.addMapping(model.getKey(), db.getFilePointer());
        db.write(model.toByteArray());
    }

    @Override
    public void set(String key, Model model) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void remove(String key) {

    }

    /**
     * Returns object offset by its key
     * @param key unique key of object
     * @return (long) offset
     * @throws IOException
     */
    private long allocateKey(String key) throws IOException{
    	long first = md.getMapping(0).offset;
		return first;
    }

}
