package lib;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

/**
 * This class is dispatcher for indexes.
 * Indexes are offsets of mappings saved in key order.
 * This class contains internal container for indexes (e.g. LinkedList). When you add new index, internal container synchronizes with
 * with its file representation.  
 *
 */
public class MappingIndexer {
	/**
	 * Container in RAM, where indexes stores
	 */
    private LinkedList<Long> indexesContainer = new LinkedList<Long>();
    
    /**
     * File on disk, fully matches with indexesContainer
     */
    private RandomAccessFile indexesFileRepresentation;
    
    /**
     * Initializing new MappingIndexer from file. If file exists, MappingIndexer will import all it content into indexes container.
     * If file not exists, MappingIndexer will create new empty file and new empty indexes container
     * @param indexesFile filename
     * @throws IOException
     */
    public MappingIndexer(String indexesFile) throws IOException {
        indexesFileRepresentation = new RandomAccessFile(indexesFile, "rw");

        for(long i = 0; i < indexesFileRepresentation.length() / (Long.SIZE / 8); i++){
            indexesContainer.add((int) i, indexesFileRepresentation.readLong());
        }
    }

    /**
     * Adds new index on position offset. Synchronize indexes container into file
     * @param offset
     * @param index
     * @throws IOException
     */
    public void addIndex(long offset,long index) throws IOException {
        indexesContainer.add((int) offset, index);
        this.save();
    }

    /**
     * Retrieving index by it offset
     * @param offset
     * @return index
     */
    public long getIndex(long offset){
        return indexesContainer.get((int) offset);
    }
    
    /**
     * Synchronizing indexes container into file
     * @throws IOException
     */
    private void save() throws IOException {
        indexesFileRepresentation.seek(0);

        for(long l : indexesContainer) {
            indexesFileRepresentation.writeLong(l);
        }
    }
    
    /**
     * Closing mapping indexer file after using. Must be called to prevent file destruction
     * @throws IOException
     */
    public void close() throws IOException{
    	indexesFileRepresentation.close();
    }
}
