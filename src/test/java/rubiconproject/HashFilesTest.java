package rubiconproject;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class HashFilesTest {

    @Test
    public void fileHash() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String hash = HashFiles.fileHash(new File(classLoader.getResource("fileA.dat").getFile()));

        System.out.println(hash);
        assertTrue(hash.startsWith("af371785c4"));
    }

}