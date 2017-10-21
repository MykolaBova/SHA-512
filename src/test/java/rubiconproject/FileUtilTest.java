package rubiconproject;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileUtilTest extends AbstractTest {

    @Test
    public void readFile() {
        byte[] bytes = FileUtil.readFile(new File(classLoader.getResource(FILE_A).getFile()));
        assertEquals(FILE_A_CONTENT, new String(bytes));
    }

    @Test
    public void fixWindowsArray() {
        byte[] bytes = {14, 13, 10, 15};
        byte[] fixed = FileUtil.fixWindowsArray(bytes);
        assertEquals(3, fixed.length);
        assertEquals(14, fixed[0]);
        assertEquals(10, fixed[1]);
        assertEquals(15, fixed[2]);
    }
}