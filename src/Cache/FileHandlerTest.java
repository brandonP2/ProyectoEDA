package Cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileHandlerTest {
    @Test
    public void testCreateFile() {//correr este test solo
        try {
            assertTrue (FileHandler.createFile("test.txt"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testExistFile() {
        String fileName = "test.txt";
        FileHandler.createFile(fileName);
        boolean result = FileHandler.existFile(fileName);
        assertTrue(result);
    }

    @Test
    public void testDeleteFile() {
        String fileName = "test.txt";
        FileHandler.createFile(fileName);
        FileHandler.deleteFile(fileName);
        assertFalse(FileHandler.existFile(fileName));
    }


}
