package rubiconproject;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class HashFilesTest {

    public static final String FILE_A_HASH = "af371785c4fecf30acdd648a7d4d649901eeb67536206a9f517768f0851c0a06616f724b2a194e7bc0a762636c55fc34e0fcaf32f1e852682b2b07a9d7b7a9f9";
    public static final String FILE_B_HASH = "46868d0a185e942d2fd15739b60096feab4ccdc99139cca4c9db82325606115c8803a6bffe37d6e54c791330add6e1fc861bfa79399f01cc88eed3fcedce13d4";
    public static final String FILE_C_HASH = "c1e42aa0c8908c9c3d49879a4fc04a59a755735418ddc3a200e911673da188bf46f67818972eac54b38422895391c82b2b0e0cf34aea9468c3ad73c2d0ffa912";
    public static final String FILE_D_HASH = "9dd88c920d86ac24112eb692e87b047bb6e69cd413593b009af62a29a71daa68f094dd3340976ae9b8e5d8e5d66d964179409c049103f91f3ccba80d9de63b7a";
    public static final String FILE_E_HASH = "40c9964826072dbebe00ea99db34a8c8268088738de8d2a9c02743d0eed36a018adf122bacd789cc569ba2f5f54c75191683e3f252486bf71a5824ae99e20017";

    @Test
    public void fileHash() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String hash = HashFiles.fileHash(new File(classLoader.getResource("fileA.dat").getFile()));
        assertEquals(FILE_A_HASH, hash);

        hash = HashFiles.fileHash(new File(classLoader.getResource("fileB.dat").getFile()));
        assertEquals(FILE_B_HASH, hash);
        hash = HashFiles.fileHash(new File(classLoader.getResource("fileC.dat").getFile()));
        assertEquals(FILE_C_HASH, hash);
        hash = HashFiles.fileHash(new File(classLoader.getResource("fileD.dat").getFile()));
        assertEquals(FILE_D_HASH, hash);
        hash = HashFiles.fileHash(new File(classLoader.getResource("fileE.dat").getFile()));
        assertEquals(FILE_E_HASH, hash);
    }

}