package lib;

/**
 * Created by IntelliJ IDEA.
 * User: Stas
 * Date: 14.10.12
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public abstract class Model {
    public static int getSerializedLength(){
    	return 0;
    }
    
    public abstract byte[] toByteArray();
    public abstract String getKey();
}
