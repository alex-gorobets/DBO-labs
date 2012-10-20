package lib;

public class Mapping {
	public String key;
	public long offset;
	
	@Override
	public String toString(){
		return "Mapping: <" + key + ", " + offset + ">";
	}
}
