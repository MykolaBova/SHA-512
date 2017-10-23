package rubiconproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rubiconproject.service.FileHashService;
import rubiconproject.service.FileHashServiceImpl;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * Copyright © 2016 Rubicon Project, All rights reserved.
 */
public class HashFiles {

    private static final Logger LOG = LogManager.getLogger(HashFiles.class);
    private static final FileHashService FILE_HASH_SERVICE = new FileHashServiceImpl();

    public static void main(String[] argv) throws IOException {
        if (argv.length < 1) {
            System.err.println("one arguments expected: <path>");
            System.exit(1);
        }
        // Implement a program to calculate hash of the specified directory or file
        String path = argv[0];
        LOG.info("Receive path {}", path);
        FileWithHash result = FILE_HASH_SERVICE.recursionHash(Paths.get(path).toFile());
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
            for (FileWithHash fwh : internalFiles) {
                printResult(fwh, pref.concat("\t"));
            }
        }
    }

}
