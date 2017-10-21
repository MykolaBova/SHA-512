package rubiconproject.hash;

import org.junit.Test;

import static org.junit.Assert.*;

public class Sha512HashGeneratorTest {
    public static final String FILE_A = "Nullam ornare, magna ac tincidunt congue, diam nisl vulputate velit, sed auctor nibh ex quis nisi. Duis suscipit purus a elementum sollicitudin. Morbi non eros vitae diam ornare auctor quis ac ipsum. Vestibulum et odio vitae mauris luctus imperdiet at quis nulla. Proin sem sapien, vestibulum sit amet vehicula vitae, sodales nec lacus. Praesent eget aliquet sem. Fusce malesuada semper luctus. Integer hendrerit neque erat, vitae dignissim justo vestibulum vel. Morbi eu purus a elit vulputate congue. Vivamus congue mattis quam et eleifend. Maecenas hendrerit elit non rutrum cursus. Vestibulum sodales arcu quis nisl ultricies maximus. Quisque turpis eros, pellentesque et nisl sit amet, elementum mollis eros. In ut massa sed lorem volutpat egestas sed elementum ex.\n";

    @Test
    public void getHash() {
        HashGenerator sha = new Sha512HashGenerator();
        String hash = sha.getHash("hello world".getBytes());
        System.out.println(hash);
        assertTrue(hash.startsWith("309ecc"));
    }

    @Test
    public void hashFileA() {
        HashGenerator sha = new Sha512HashGenerator();
        String hash = sha.getHash(FILE_A.getBytes());
        System.out.println(hash);
        assertEquals("af371785c4fecf30acdd648a7d4d649901eeb67536206a9f517768f0851c0a06616f724b2a194e7bc0a762636c55fc34e0fcaf32f1e852682b2b07a9d7b7a9f9", hash);
    }
}