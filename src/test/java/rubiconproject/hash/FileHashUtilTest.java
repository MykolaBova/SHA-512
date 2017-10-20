package rubiconproject.hash;

import org.junit.Test;
import rubiconproject.AbstractTest;
import rubiconproject.FileHash;
import rubiconproject.HashFiles;

import java.io.File;

import static org.junit.Assert.*;

public class FileHashUtilTest extends AbstractTest {

    @Test
    public void dirHash() throws Exception {
//        String hash = FileHashUtil.dirHash(new File(classLoader.getResource(DIR_INPUT).getFile()));
//        assertEquals(DIR_INPUT_HASH, hash);

    }

    @Test
    public void fileHash() throws Exception {
        FileHash fileHash = FileHashUtil.fileHash(new File(classLoader.getResource(FILE_A).getFile()));
        assertEquals(FILE_A_HASH, fileHash.getHash());
        fileHash = FileHashUtil.fileHash(new File(classLoader.getResource(FILE_B).getFile()));
        assertEquals(FILE_B_HASH, fileHash.getHash());
        fileHash = FileHashUtil.fileHash(new File(classLoader.getResource(FILE_C).getFile()));
        assertEquals(FILE_C_HASH, fileHash.getHash());
        fileHash = FileHashUtil.fileHash(new File(classLoader.getResource(FILE_D).getFile()));
        assertEquals(FILE_D_HASH, fileHash.getHash());
        fileHash = FileHashUtil.fileHash(new File(classLoader.getResource(FILE_E).getFile()));
        assertEquals(FILE_E_HASH, fileHash.getHash());
    }

}