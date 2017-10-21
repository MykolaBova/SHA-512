package rubiconproject.hash;

import org.junit.Test;
import rubiconproject.AbstractTest;

import static org.junit.Assert.*;

public class Sha512HashGeneratorTest extends AbstractTest {

    @Test
    public void getHash() {
        String hash = GENERATOR.getHash("hello world".getBytes());
        System.out.println(hash);
        assertTrue(hash.startsWith("309ecc"));
    }

    @Test
    public void hashFileA() {
        String hash = GENERATOR.getHash(FILE_A.getBytes());
        System.out.println(hash);
        assertEquals("af371785c4fecf30acdd648a7d4d649901eeb67536206a9f517768f0851c0a06616f724b2a194e7bc0a762636c55fc34e0fcaf32f1e852682b2b07a9d7b7a9f9", hash);
    }
}