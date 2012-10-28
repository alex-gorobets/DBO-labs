package lib.mappings;

/**
 * Mapping is pair <Key, Offset in .db file>
 */
public class Mapping {
	/**
	 * Indexing key
	 */
	private String key;
	
	/**
	 * Offset in database file
	 */
	private long offset;
	
	
	/**
	 * Creating new mapping
	 * @param key
	 * @param offset
	 */
	public Mapping(String key, long offset) {
		super();
		this.key = key;
		this.offset = offset;
	}

	/**
	 * Getter for key
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Getter for offset
	 * @return offset in database file
	 */
	public long getOffset() {
		return offset;
	}

	@Override
	public String toString(){
		return "Mapping: <" + key + ", " + offset + ">";
	}
}
