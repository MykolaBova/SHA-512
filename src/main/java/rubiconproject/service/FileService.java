package rubiconproject.service;

import java.io.File;
import java.io.IOException;

/**
 * Read file content
 */
public interface FileService {

    /**
     * Read {@link File} content and return byte array.
     * If {@link File#isDirectory()}, throw new {@link IllegalArgumentException}
     * If {@link IOException} happens, throw new {@link RuntimeException}
     *
     * @param file file to read
     * @return byte array or {@link IllegalArgumentException} or {@link RuntimeException}
     */
    byte[] readFile(File file);
}
