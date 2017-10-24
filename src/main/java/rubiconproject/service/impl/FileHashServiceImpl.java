package rubiconproject.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rubiconproject.FileWithHash;
import rubiconproject.hash.HashGenerator;
import rubiconproject.service.FileHashService;
import rubiconproject.service.FileService;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FileHashServiceImpl implements FileHashService {
    private static final Logger LOG = LogManager.getLogger(FileHashServiceImpl.class);
    private final HashGenerator generator;
    private final FileService fileService;

    public FileHashServiceImpl(HashGenerator generator, FileService fileService) {
        this.generator = generator;
        this.fileService = fileService;
    }

    @Override
    public FileWithHash recursionHash(File file) {
        if (file.isDirectory()) {
            List<FileWithHash> internalList = new LinkedList<>();
            List<FileWithHash> collect = Arrays.stream(file.listFiles())
                    .sorted()
                    .map(this::recursionHash)
                    .collect(toList());
            internalList.addAll(collect);
            return FileWithHash.builder()
                    .setFileName(file.getName())
                    .setHash(dirHash(file, internalList))
                    .setInternalFiles(internalList)
                    .build();
        } else {
            return fileHash(file);
        }
    }

    @Override
    public String dirHash(File file, List<FileWithHash> internalList) {
        StringBuilder builder = new StringBuilder();
        internalList.forEach(f -> builder.append(f.getHash()));
        String hash = generator.getHash(builder.toString().getBytes());
        LOG.debug("Dir {} hash is {}", file.getName(), hash);
        return hash;
    }

    @Override
    public FileWithHash fileHash(File file) {
        byte[] content = fileService.readFile(file);
        String hash = generator.getHash(content);
        LOG.debug("File {} hash is {}", file.getName(), hash);

        return FileWithHash.builder()
                .setFileName(file.getName())
                .setHash(hash)
                .build();
    }
}
