package rubiconproject.hash;

import org.junit.Test;

import static org.junit.Assert.*;

public class Sha512HashGeneratorTest {

    @Test
    public void getHash() {
        HashGenerator sha = new Sha512HashGenerator();

        assertTrue(sha.getHash("hello world").startsWith("309ecc"));
    }
}