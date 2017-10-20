package rubiconproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.FileUtils;
import rubiconproject.hash.HashGenerator;
import rubiconproject.hash.Sha512HashGenerator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

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
//        Files.walk(Paths.get(path))
//                .forEach(System.out::println);
        List<FileHash> list = new LinkedList<>();
        List<FileHash> fileHashes = recursionHash(Paths.get(path).toFile(), list);
        fileHashes.forEach(System.out::println);
    }

    static List<FileHash> recursionHash(File file, List<FileHash> fileHashList)  {
        try {
            if (file.isDirectory()) {
                fileHashList.add(FileHash.builder().setFileName(file.getName()).build());
                File[] files = file.listFiles();
                for (File f : files) {
                    recursionHash(f, fileHashList);
                }
            } else {
                fileHashList.add(fileHash(file));
            }
        } catch (IOException e) {
            LOG.error(e);
        }
        return fileHashList;
    }

    static FileHash fileHash(File file) throws IOException {
        String content = readFile(file);
        String hash = generator.getHash(content);
        LOG.info("File {} hash is {}", file.getName(), hash);

        return FileHash.builder()
                .setFileName(file.getName())
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
