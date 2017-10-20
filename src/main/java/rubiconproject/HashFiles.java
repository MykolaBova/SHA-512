package rubiconproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rubiconproject.hash.HashGenerator;
import rubiconproject.hash.Sha512HashGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Copyright © 2016 Rubicon Project, All rights reserved.
 */
public class HashFiles {

    private static final Logger LOG = LogManager.getLogger(HashFiles.class);

    static HashGenerator generator = new Sha512HashGenerator();

    public static void main(String[] argv) throws IOException {
        if (argv.length < 1) {
            System.err.println("one arguments expected: <path>");
            System.exit(1);
        }
        // Implement a program to calculate hash of the specified directory or file
        String path = argv[0];
        LOG.info("Receive path {}", path);
        FileHash fileHashes = recursionHash(Paths.get(path).toFile());
        System.out.println(fileHashes);
    }

    static FileHash recursionHash(File file)  {
        try {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                List<FileHash> internalList = new LinkedList<>();
                for (File f : files) {
                    internalList.add(recursionHash(f));
                }
                return FileHash.builder()
                        .setFileName(file.getName())
                        .setDirectory(true)
                        .setHash(dirHash(file, internalList))
                        .setInternalFiles(internalList)
                        .build();
            } else {
                return fileHash(file);
            }
        } catch (IOException e) {
            LOG.error(e);
        }
        return null;
    }

    private static String dirHash(File file, List<FileHash> internalList) {
        StringBuilder builder = new StringBuilder();
        for (FileHash fh : internalList) {
            builder.append(fh.getHash());
        }
        String hash = generator.getHash(builder.toString());
        LOG.info("Dir {} hash is {}", file.getName(), hash);
        return hash;
    }

    static FileHash fileHash(File file) throws IOException {
        String content = readFile(file);
        String hash = generator.getHash(content);
        LOG.info("File {} hash is {}", file.getName(), hash);

        return FileHash.builder()
                .setFileName(file.getName())
                .setDirectory(false)
                .setHash(hash)
                .build();
    }



    static String readFile(File file) {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Directories not supported here");
        }
        try (FileInputStream reader = new FileInputStream(file)) {
            byte[] contentArray = new byte[reader.available()];
            reader.read(contentArray);
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
