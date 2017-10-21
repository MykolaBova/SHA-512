package rubiconproject.hash;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import rubiconproject.AbstractTest;

import static org.junit.Assert.*;

public class Sha512HashGeneratorTest extends AbstractTest {

    @Test
    public void getHash() {
        String hash = GENERATOR.getHash("hello world".getBytes());
        assertThat(hash, CoreMatchers.startsWith("309ecc"));
    }

    @Test
    public void hashFileA() {
        String hash = GENERATOR.getHash(FILE_A_CONTENT.getBytes());
        assertEquals(FILE_A_HASH, hash);
    }
}