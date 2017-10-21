package rubiconproject.hash;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Calculate hash using SHA-512 algorithm
 */
public class Sha512HashGenerator implements HashGenerator {
    private static final Logger LOG = LogManager.getLogger(Sha512HashGenerator.class);
    private static final String ALGORITHM = "SHA-512";

    @Override
    public String getHash(byte[] byteArray) {
        String hashedString = "";
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            byte[] bytes = md.digest(byteArray);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedString = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            LOG.error("Hash algorithm error: {}", e.getMessage());
        }
        return hashedString;
    }
}
