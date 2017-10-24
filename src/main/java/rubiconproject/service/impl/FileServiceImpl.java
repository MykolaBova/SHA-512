package rubiconproject.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rubiconproject.service.FileService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileServiceImpl implements FileService {
    private static final Logger LOG = LogManager.getLogger(FileServiceImpl.class);

    @Override
    public byte[] readFile(File file) {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Directories not supported here");
        }
        try (FileInputStream reader = new FileInputStream(file)) {
            byte[] contentArray = new byte[reader.available()];
            reader.read(contentArray);

            // Need to change new line '\r\n' to '\n'
            // Remove '\r' symbol (13)
            byte[] result = removeWrongByte(contentArray);
            LOG.info("Read from file {} {} bytes", file.getName(), result.length);
            return result;
        } catch (IOException e) {
            LOG.error("Failed to read file {}", file.getName());
        }
        throw new RuntimeException("Filed to read file " + file.getName());
    }

    /**
     * Fix byte array: remove /r symbol (byte 13) from array
     *
     * @param contentArray byte array
     * @return new byte array without 13 byte
     */
    private byte[] removeWrongByte(byte[] contentArray) {
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
