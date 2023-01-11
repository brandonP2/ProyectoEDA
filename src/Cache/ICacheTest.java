package Cache;

import Cache.Exception.KeyNotFoundException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
class ICacheTest {

    @Test
    void testGet() throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        cache.put("key", "value");
        assertEquals("value", cache.get("key"));
    }

    @Test
    void testPut() throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        cache.put("key", "value");
        assertEquals("value", cache.get("key"));
    }

    @Test
    void testPutNull() throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        cache.put("key", null);
        assertNull(cache.get("key"));
    }

    @Test
    void testPutTwice() throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        cache.put("key", "value");
        cache.put("key", "value2");
        assertEquals("value2", cache.get("key"));
    }

    @Test
    void testPutTwiceNull() throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        cache.put("key", "value");
        cache.put("key", null);
        assertNull(cache.get("key"));
    }

    @Test
    void testPutTwiceNull2() throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        cache.put("key", null);
        cache.put("key", "value");
        assertEquals("value", cache.get("key"));
    }


}