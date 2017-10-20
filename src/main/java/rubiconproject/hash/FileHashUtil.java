package rubiconproject.hash;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rubiconproject.FileHash;
import rubiconproject.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class FileHashUtil {
    private static final Logger LOG = LogManager.getLogger(FileHashUtil.class);
    private static final HashGenerator GENERATOR = new Sha512HashGenerator();

    /**
     *
     * @param file
     * @param internalList
     * @return
     */
    public static String dirHash(File file, List<FileHash> internalList) {
        StringBuilder builder = new StringBuilder();
        for (FileHash fh : internalList) {
            builder.append(fh.getHash());
        }
        String hash = GENERATOR.getHash(builder.toString());
        LOG.info("Dir {} hash is {}", file.getName(), hash);
        return hash;
    }

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static FileHash fileHash(File file) throws IOException {
        String content = FileUtil.readFile(file);
        String hash = GENERATOR.getHash(content);
        LOG.info("File {} hash is {}", file.getName(), hash);

        return FileHash.builder()
                .setFileName(file.getName())
                .setDirectory(false)
                .setHash(hash)
                .build();
    }
}
