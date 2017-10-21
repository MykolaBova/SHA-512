package rubiconproject.hash;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rubiconproject.AbstractTest;
import rubiconproject.FileWithHash;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileHashUtilTest extends AbstractTest {

    @Mock
    private FileWithHash fileWithHash1;

    @Mock
    private FileWithHash fileWithHash2;

    @Before
    public void init() {
        when(fileWithHash1.getHash()).thenReturn("123");
        when(fileWithHash2.getHash()).thenReturn("456");
    }
    @Test
    public void dirHash() throws Exception {
        String hash = FileHashUtil.dirHash(new File(classLoader.getResource(DIR_INPUT).getFile()), Arrays.asList(fileWithHash1, fileWithHash2));
        assertEquals(GENERATOR.getHash("123456".getBytes()), hash);
    }

    @Test
    public void fileHash() throws Exception {
        FileWithHash fileWithHash = FileHashUtil.fileHash(new File(classLoader.getResource(FILE_A).getFile()));
        assertEquals(FILE_A_HASH, fileWithHash.getHash());
        assertThat(FILE_A, CoreMatchers.endsWith(fileWithHash.getFileName()));
        assertTrue(Objects.isNull(fileWithHash.getInternalFiles()));
        fileWithHash = FileHashUtil.fileHash(new File(classLoader.getResource(FILE_B).getFile()));
        assertEquals(FILE_B_HASH, fileWithHash.getHash());
        fileWithHash = FileHashUtil.fileHash(new File(classLoader.getResource(FILE_C).getFile()));
        assertEquals(FILE_C_HASH, fileWithHash.getHash());
        fileWithHash = FileHashUtil.fileHash(new File(classLoader.getResource(FILE_D).getFile()));
        assertEquals(FILE_D_HASH, fileWithHash.getHash());
        fileWithHash = FileHashUtil.fileHash(new File(classLoader.getResource(FILE_E).getFile()));
        assertEquals(FILE_E_HASH, fileWithHash.getHash());
    }

    @Test
    public void recursionHash() throws Exception {
        FileWithHash fileWithHash = FileHashUtil.recursionHash(new File(classLoader.getResource(DIR_INPUT).getFile()));
        assertEquals(DIR_INPUT_HASH, fileWithHash.getHash());
        assertEquals(DIR_INPUT, fileWithHash.getFileName());
        assertTrue(Objects.nonNull(fileWithHash.getInternalFiles()));
        assertFalse(fileWithHash.getInternalFiles().isEmpty());
    }

}