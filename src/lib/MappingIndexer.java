package lib;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Stas
 * Date: 14.10.12
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */
public class MappingIndexer {
    private LinkedList<Long> indexes = new LinkedList<Long>();
    RandomAccessFile f;
    public MappingIndexer(String indexFile) throws IOException {
        f = new RandomAccessFile(indexFile, "rw");

        for(long i = 0; i < f.length(); i++){
            indexes.add((int) i, f.readLong());
        }
    }
    
    public void save() throws IOException {
        f.seek(0);

        for(long l : indexes) {
            f.writeLong(l);
        }

        System.out.println(indexes);
    }

    public void set(long key,long offset) throws IOException {
        System.out.println(key + " " + offset);
        indexes.add((int) key, offset);
        this.save();
    }

    public long getOffset(long index){
        if(indexes.size() == 0)
            return 0;
        else
            return indexes.get((int) index);
    }
}
