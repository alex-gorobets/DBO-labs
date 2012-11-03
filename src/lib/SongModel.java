package lib;

import java.util.Arrays;
import org.json.*;


public class SongModel{
    public final static int SERIALIZED_LENGTH = 104;

    private String title;
    private String artist;
    private String album;
    private Integer year;
    private float duration;
    
    public SongModel(String title, String artist, String album, Integer year, float duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.duration = duration;
    }

    public SongModel(JSONObject json) throws JSONException{
    	this(json.getString("title"),
    			json.getString("artist"),
    			json.getString("album"),
    			json.getInt("year"),
    			(float) json.getDouble("duration"));
    }

    public SongModel(byte[] in) {
        //!TODO remove hard coding
        this(ByteConverter.bytesToString(Arrays.copyOfRange(in, 0, 32)),
        		ByteConverter.bytesToString(Arrays.copyOfRange(in, 32, 64)),
        		ByteConverter.bytesToString(Arrays.copyOfRange(in, 64, 96)),
        		ByteConverter.bytesToInt(Arrays.copyOfRange(in, 96, 100)),
        		ByteConverter.bytesToFloat(Arrays.copyOfRange(in, 100, 104)));
    }
    
    public byte[] toByteArray() {
        byte[] out = new byte[SERIALIZED_LENGTH];
        int offset = 0;

        System.arraycopy(ByteConverter.getBytes(getTitle()), 0, out, offset, ByteConverter.STRING_LENGTH);
        offset += ByteConverter.STRING_LENGTH;

        System.arraycopy(ByteConverter.getBytes(getArtist()), 0, out, offset,ByteConverter.STRING_LENGTH);
        offset += ByteConverter.STRING_LENGTH;

        System.arraycopy(ByteConverter.getBytes(getAlbum()), 0, out, offset,ByteConverter.STRING_LENGTH);
        offset += ByteConverter.STRING_LENGTH;

        System.arraycopy(ByteConverter.getBytes(getYear()), 0, out, offset,ByteConverter.INT_LENGTH);
        offset += ByteConverter.INT_LENGTH;

        System.arraycopy(ByteConverter.getBytes(getDuration()), 0, out, offset,ByteConverter.FLOAT_LENGTH);

        return out;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object obj){
    	SongModel model;
    	if(obj instanceof SongModel){
    		model = (SongModel) obj;
    	} else
    		return false;
    	
        return this.title.equals(model.getTitle()) && this.artist.equals(model.getArtist()) && this.album.equals(model.getAlbum())
                && this.year.equals(model.getYear()) && this.duration == getDuration();
    }

    @Override
    public String toString() {
        return "--" + getKey() + "--" + artist + " " + title + " " + album + " " + year + " " + duration;
    	//return getKey();
    }
    
    public String getKey(){
    	return title;
    }
    
    public JSONObject toJson() throws JSONException{
    	return new JSONObject()
		.put("key", getKey())
    	.put("artist", artist)
    	.put("title", title)
    	.put("album", album)
    	.put("year", year)
		.put("duration", duration);
    }
}
