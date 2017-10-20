package rubiconproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 */
public class FileUtil {
    private static final Logger LOG = LogManager.getLogger(FileUtil.class);

    /**
     *
     * @param file
     * @return
     */
    public static String readFile(File file) {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Directories not supported here");
        }
        try (FileInputStream reader = new FileInputStream(file)) {
            byte[] contentArray = new byte[reader.available()];
            reader.read(contentArray);

            // For some reason with '\r' hash is wrong, so we need to remove this symbol
            String contentString = new String(contentArray).replaceAll("\r", "");
            LOG.info("Read file {}", file.getName());
            LOG.debug("File {} contains: {}", file.getName(), contentString);
            return contentString;
        } catch (IOException e) {
            LOG.error("Failed to read file {}", file.getName());
        }
        throw new RuntimeException("Filed to read file " + file.getName());
    }
}
