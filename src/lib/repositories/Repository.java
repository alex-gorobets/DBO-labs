package lib.repositories;

import java.io.IOException;

import lib.SongModel;

public interface  Repository {

    public SongModel[] get() throws IOException;
    public SongModel[] get(int limit, int offset) throws IOException;
    public SongModel get(String key) throws IOException;
    
    public void add(SongModel model) throws IOException;
    void set(SongModel model) throws IOException;
    public void remove(String key) throws IOException;


    long size() throws IOException;
}
