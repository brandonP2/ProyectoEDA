package App;

import Cache.ICache;
import Cache.Exception.DuplicatedKeyException;
import Cache.Exception.KeyNotFoundException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;


@Command (name = "Cache", version = "Cache 1",
        mixinStandardHelpOptions = true,
        description = "Cache is a small, fast memory storage area that stores frequently accessed data.")
public class CacheDB implements Callable<Integer> {

    @Command(name="displayAll",
            description = "This command can display all the keys that are in the cache.")
    public Integer displayAll() throws Exception {
        ICache cache = new ICache();
        try {
            String[] keys = cache.getAll();
            for (String key : keys) {
                System.out.println(key);
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }


    @Command(name="get", description = "This get the value of the key")
    public Integer get(@Parameters(arity = "1", paramLabel = "keyy",description = "This is the key") String key) throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        String localKey = key;
        try {
            System.out.println(cache.get(localKey));
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }


    @Command(name = "add", description = "This can add a value, this value would associated to a key.")
    public Integer add(
            @Parameters(arity = "1", paramLabel = "Key", description = "This is the key that you can use in the command") String key,
            @Parameters(arity = "1", paramLabel = "Value", description = "This value you can use it in the command.") String value) throws IOException, DuplicatedKeyException {
        ICache cache = new ICache();
        try {
            cache.addNew(key, value);
            System.out.println(value + " you added into " + key);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }



    @Command(name = "put", description = "Put update the value of the key. PD: The value has to be in apostrophes.")
    public Integer put(
            @Parameters(arity = "1", paramLabel = "Key", description = "This is the key that you can use in the command.") String key,
            @Parameters(arity = "1", paramLabel = "Value", description = "This value you can use it in the command.") String value) throws IOException {
        ICache cache = new ICache();
        try {
            cache.put(key, value);
            System.out.println(key + " updated with " + value);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Command(name = "delete", description = "This deletes the key and its value.")
    public Integer delete(@Parameters(arity = "1", paramLabel = "Key", description = "This is the key that you can use in the command.") String key) throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        try {
            cache.remove(key);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Command(name = "size", description = "With this you can get the real size of the cache.")
    public Integer size() throws IOException {
        ICache cache = new ICache();
        try {
            System.out.println(cache.size());
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }


    @Command(name="check", description = "With this you can check if the key really exits.")
    public Integer checkKey(@Parameters(arity = "1", paramLabel = "Key",description = "Master Key") String key) throws IOException {
        ICache cache = new ICache();
        try {
            boolean result = cache.exists(key);
            System.out.println(result);
            return result ? 0 : -1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

    @Command(name="exit", description = "With this you exit the program.")
    public Integer exit () {
        System.exit(0);
        return 0;
    }

    @Command(name="clearAll", description = "With this you can clear all the cache.")
    public Integer clearAll() throws IOException {
        //With this you can clear all the cache.
        ICache cache1 = new ICache();
        for (String key : cache1.getAll()) {
            cache1.remove(key);
        }
        System.out.println("Cache is empty");
        return 0;
    }


    @Override
    public Integer call() throws Exception {
        System.out.println("Commands: displayAll, get, add, put, delete, size, check, exit, clearAll");
        return 0;
    }


    public static void main(String[] args) {
        int exitCode = new CommandLine(new CacheDB()).execute(args);
        System.exit(exitCode);
    }

}
