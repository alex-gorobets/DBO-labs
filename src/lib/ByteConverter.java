package lib;

import java.nio.ByteBuffer;

public class ByteConverter {
    public static final int STRING_LENGTH = 32;
    public static final int INT_LENGTH = 4;
    public static final int FLOAT_LENGTH = 4;
    public static final int LONG_LENGTH = 8;

    public static byte[] getBytes(String str){
        byte[] out = new byte[STRING_LENGTH];
        byte[] bytes = str.getBytes();
        
        for(int i = 0; i < STRING_LENGTH && i < bytes.length; i++)
           out[i] = bytes[i];

        return out;
    }

    public static byte[] getBytes(int value) {
        return new byte[] {

                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }
    
    public static byte[] getBytes(float value){
        return getBytes(Float.floatToRawIntBits(value));
    }

    public static byte[] getBytes(long value){
        ByteBuffer bb = ByteBuffer.allocate(8);
        return bb.putLong(value).array();
    }

    public static String bytesToString(byte[] bytes){
        return new String(bytes).trim();
    }

    public static int bytesToInt(byte[] bytes){
        return ByteBuffer.wrap(bytes).getInt();
    }

    public static float bytesToFloat(byte[] bytes){
        return ByteBuffer.wrap(bytes).getFloat();
    }
}
