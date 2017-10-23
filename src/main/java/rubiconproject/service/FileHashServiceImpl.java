package rubiconproject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rubiconproject.FileWithHash;
import rubiconproject.hash.HashGenerator;
import rubiconproject.hash.Sha512HashGenerator;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileHashServiceImpl implements FileHashService {
    private static final Logger LOG = LogManager.getLogger(FileHashServiceImpl.class);
    private static final HashGenerator GENERATOR = new Sha512HashGenerator();
    private static final FileService FILE_SERVICE = new FileServiceImpl();

    @Override
    public FileWithHash recursionHash(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            List<FileWithHash> internalList = new LinkedList<>();
            for (File f : files) {
                internalList.add(recursionHash(f));
            }
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
        for (FileWithHash fh : internalList) {
            builder.append(fh.getHash());
        }
        String hash = GENERATOR.getHash(builder.toString().getBytes());
        LOG.debug("Dir {} hash is {}", file.getName(), hash);
        return hash;
    }

    @Override
    public FileWithHash fileHash(File file) {
        byte[] content = FILE_SERVICE.readFile(file);
        String hash = GENERATOR.getHash(content);
        LOG.debug("File {} hash is {}", file.getName(), hash);

        return FileWithHash.builder()
                .setFileName(file.getName())
                .setHash(hash)
                .build();
    }
}
