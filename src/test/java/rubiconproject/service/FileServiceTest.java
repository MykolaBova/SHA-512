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
}