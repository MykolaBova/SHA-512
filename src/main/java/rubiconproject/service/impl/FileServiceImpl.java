package rubiconproject.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rubiconproject.service.FileService;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileServiceImpl implements FileService {
    private static final Logger LOG = LogManager.getLogger(FileServiceImpl.class);

    @Override
    public byte[] readFile(File file) {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Directories not supported here");
        }

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))){
            byte[] content = new byte[inputStream.available()];
            inputStream.read(content);
            return content;
        } catch (FileNotFoundException e) {
            LOG.error("File {} not found", file.getName());
        } catch (IOException e) {
            LOG.error("Failed to read file {}", file.getName());
        }
        throw new RuntimeException("Filed to read file " + file.getName());
    }
}
