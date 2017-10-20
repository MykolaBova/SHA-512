package rubiconproject;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rubiconproject.hash.HashGenerator;
import rubiconproject.hash.Sha512HashGenerator;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

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
        Files.walk(Paths.get(argv[0]))
                .forEach(System.out::println);
    }

    static String fileHash(File file) {
        String content = readFile(file);
        String hash = generator.getHash(content);
        LOG.info("File {} hash is {}", file.getName(), hash);

        return hash;
    }

    static String readFile(File file) {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Directories not supported here");
        }
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file))){
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
