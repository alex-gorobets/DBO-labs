package lib;

import java.io.IOException;

public interface  Repository {

    public Model[] get();
    public Model[] get(int limit, int offset);
    public Model get(String key) throws IOException;
    
    public void add(Model model) throws IOException;
    public void set(String key, Model model);
    public void remove(String key);


    long size();
}
