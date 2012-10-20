package lib;

import java.io.IOException;

public interface  Repository {

    public Model[] get();
    public Model[] get(int limit, int offset);
    public Model get(String key) throws IOException;
    
    public void add(Model model) throws IOException;
    public void set(int key, Model model);
    public void remove(int key);
	void set(String key, Model model);
}
