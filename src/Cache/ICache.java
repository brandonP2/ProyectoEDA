package Cache;

import Cache.Exception.DuplicatedKeyException;
import Cache.Exception.KeyNotFoundException;
import Structure.TreeMap;
import java.io.IOException;

public class ICache implements ICacheInterface {
    private final String dirname = "cache";
    private final TreeMap<String, String> cache;

//This is the constructor of the class ICache
    public ICache() throws IOException {
        if(!FileHandler.existFile(this.dirname)){
            FileHandler.createFolder(this.dirname);
            cache = new TreeMap<>();
        }else{
            this.cache = new TreeMap<>();
            String[] keys = FileHandler.readFolder(this.dirname);
            for(String key : keys){
                FileHandler fileHandler = new FileHandler();
                String value = fileHandler.readFile(getFileName(key));
                this.cache.put(key, value);
            }

        }

    }
//This method can add a value, this value would associated to a key.
    public String[] getAll() {
        if (cache.isEmpty()) {
            return null;
        }
        Object[] keys = cache.keys();
        String[] output = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            output[i] = (String) keys[i];
        }
        return output;
    }

//This method can get the value of the key.
    public String get(String key) throws KeyNotFoundException {
        if (!cache.contains(key)) {
            throw new KeyNotFoundException("Key not found");
        }
        return cache.get(key);
    }

//This method get the value of the key.
    public String getOrDefault(String key, String defaultValue) {
        try {
            if (!cache.contains(key)) {
                return defaultValue;
            }
            return cache.get(key);
        } catch (KeyNotFoundException e) {
            return defaultValue;
        }

    }

//This method tells you if the key is in the cache.
    public boolean exists(String key) {
    return cache.contains(key) && FileHandler.existFile(getFileName(key));
}

//This method update the value of the key.

    public void put(String key, String value) throws IOException {
        cache.put(key, value);
        String fileName = getFileName(key);

        if(FileHandler.existFile(fileName)){
            FileHandler.writeInFile(fileName, value);
        }
    }

//This method add a new key and value.
    public void addNew(String key, String value) throws IOException {
    String fileName = getFileName(key);
    if (cache.contains(key)) {
        throw new DuplicatedKeyException("Key already exists");
    }
    cache.put(key, value);
    if(FileHandler.createFile(fileName)){
        FileHandler.writeInFile(fileName, value);
    }
}


    //This method remove the key and value.
    public boolean remove(String key) {
        if (cache.contains(key)) {
            if(FileHandler.existFile(getFileName(key))){
                FileHandler.deleteFile(getFileName(key));
                return true;
            }
        }
        System.out.println("Key not found");
        return false;
    }



    //This method count the keys and values.
    public int size() {
        return cache.size();
    }

//This method get the name of the file.
    private String getFileName(String file) {
        return this.dirname + "/" + file + ".txt";
    }



}