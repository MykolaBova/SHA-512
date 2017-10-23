package rubiconproject.service;

import org.junit.Test;
import rubiconproject.AbstractTest;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class FileServiceTest extends AbstractTest {

    @Test
    public void readFile() {
        byte[] bytes = FILE_SERVICE.readFile(new File(classLoader.getResource(FILE_A).getFile()));
        assertEquals(FILE_A_CONTENT, new String(bytes));
    }

    @Test(expected = IllegalArgumentException.class)
    public void readDirectory() {
        FILE_SERVICE.readFile(new File(classLoader.getResource(DIR_INPUT).getFile()));
    }

    @Test(expected = RuntimeException.class)
    public void readFileFail() {
        FILE_SERVICE.readFile(new File("qwr"));
    }

    /*@Test
    public void fixWindowsArray() {
        byte[] bytes = {14, 13, 10, 15};
        byte[] fixed = FILE_SERVICE.fixWindowsArray(bytes);
        assertEquals(3, fixed.length);
        assertEquals(14, fixed[0]);
        assertEquals(10, fixed[1]);
        assertEquals(15, fixed[2]);
    }*/
}