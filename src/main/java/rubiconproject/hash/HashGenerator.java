package rubiconproject.hash;

/**
 * {@link HashGenerator} calculates hash
 */
public interface HashGenerator {

    /**
     * Calculate hash for byte array
     *
     * @param byteArray
     * @return {@link String} of hash
     */
    String getHash(byte[] byteArray);
}
