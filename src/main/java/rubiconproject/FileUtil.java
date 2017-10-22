package rubiconproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Util class to read content of {@link File}
 */
public class FileUtil {
    private static final Logger LOG = LogManager.getLogger(FileUtil.class);

    /**
     * Read {@link File} content and return byte array.
     * If {@link File#isDirectory()}, throw new {@link IllegalArgumentException}
     * If {@link IOException} happens, throw new {@link RuntimeException}
     *
     * @param file file to read
     * @return byte array or {@link IllegalArgumentException} or {@link RuntimeException}
     */
    public static byte[] readFile(File file) {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Directories not supported here");
        }
        try (FileInputStream reader = new FileInputStream(file)) {
            byte[] contentArray = new byte[reader.available()];
            reader.read(contentArray);

            // In windows new line is '\r\n', but we need unix-like'\n'
            // So, in windows systems we need to remove '\r' symbol (13)
            byte[] result = fixWindowsArray(contentArray);
            LOG.info("Read from file {} {} bytes", file.getName(), result.length);
            return result;
        } catch (IOException e) {
            LOG.error("Failed to read file {}", file.getName());
        }
        throw new RuntimeException("Filed to read file " + file.getName());
    }

    /**
     * Fix byte array for windows system. This method remove byte 13 from array
     *
     * @param contentArray byte array
     * @return new byte array without 13 byte
     */
    static byte[] fixWindowsArray(byte[] contentArray) {
        int removed = 0;
        for (Byte b : contentArray) {
            if (b == 13) {
                ++removed;
            }
        }
        if (removed == 0) {
            return contentArray;
        }
        byte[] resultArray = new byte[contentArray.length - removed];
        int tmp = 0;
        for (int i = 0; i < resultArray.length + removed; i++) {
            byte b = contentArray[i];
            if (b == 13) {
                ++tmp;
            } else {
                resultArray[i - tmp] = b;
            }
        }
        return resultArray;
    }
}
