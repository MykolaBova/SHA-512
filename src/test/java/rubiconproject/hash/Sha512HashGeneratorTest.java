package rubiconproject.hash;

import org.junit.Test;

import static org.junit.Assert.*;

public class Sha512HashGeneratorTest {

    @Test
    public void getHash() {
        HashGenerator sha = new Sha512HashGenerator();
        String hash = sha.getHash("6634353932646239353864363362613162666365353137306165303263376230396631366564343564643438323334303734373461316438303665623932343935656164663366386662613235393066666638363363623466643964313336653239343836393365323465616637343365313730333239373762313532336461");
        System.out.println(hash);
        assertTrue(hash.startsWith("309ecc"));
    }
}