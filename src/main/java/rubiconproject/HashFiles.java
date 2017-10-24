package rubiconproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rubiconproject.hash.Sha512HashGenerator;
import rubiconproject.service.impl.FileHashServiceImpl;
import rubiconproject.service.impl.FileServiceImpl;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * Copyright © 2016 Rubicon Project, All rights reserved.
 */
public class HashFiles {

    private static final Logger LOG = LogManager.getLogger(HashFiles.class);

    public static void main(String[] argv) throws IOException {
        if (argv.length < 1) {
            System.err.println("one arguments expected: <path>");
            System.exit(1);
        }
        // Implement a program to calculate hash of the specified directory or file
        String path = argv[0];
        LOG.info("Receive path {}", path);
        FileHashServiceImpl fileHashService = new FileHashServiceImpl(new Sha512HashGenerator(), new FileServiceImpl());
        FileWithHash result = fileHashService.recursionHash(Paths.get(path).toFile());
        System.out.println("\n\n========================================== Result ==========================================\n");
        printResult(result, "");
    }

    private static void printResult(FileWithHash fileWithHash, String pref) {

        System.out.printf("%s %s \t\t\t %s\n",
                pref,
                fileWithHash.getFileName(),
                fileWithHash.getHash());
        List<FileWithHash> internalFiles = fileWithHash.getInternalFiles();
        if (Objects.nonNull(internalFiles) && !internalFiles.isEmpty()) {
            fileWithHash.getInternalFiles().forEach(h -> printResult(h, "\t"));
        }
    }

}
