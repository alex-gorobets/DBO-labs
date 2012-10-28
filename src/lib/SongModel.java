package lib;

import java.util.Arrays;

public class SongModel{
    public final static int SERIALIZED_LENGTH = 56;

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

    public SongModel(byte[] in) {
        //!TODO remove hard coding
        setTitle(ByteConverter.bytesToString(Arrays.copyOfRange(in, 0, 16)));
        setArtist(ByteConverter.bytesToString(Arrays.copyOfRange(in, 16, 32)));
        setAlbum(ByteConverter.bytesToString(Arrays.copyOfRange(in, 32, 48)));
        setYear(ByteConverter.bytesToInt(Arrays.copyOfRange(in, 48, 52)));
        setDuration(ByteConverter.bytesToFloat(Arrays.copyOfRange(in, 52, 56)));
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
        //return "--" + getKey() + "--" + artist + " " + title + " " + album + " " + year + " " + duration;
    	return getKey();
    }
    
    public String getKey(){
    	return title;
    }
}
