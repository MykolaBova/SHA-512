package rubiconproject;

import java.util.List;

/**
 * Result of calculation hash.
 * Contains {@link String} fileName,
 *          {@link String} hash and
 *          {@link List} of {@link FileWithHash} internal objects for directories
 */
public class FileWithHash {

    // Name of file or directory
    private String fileName;

    // Hash for file or directory
    private String hash;

    // List of internal objects (files or directories) if this is directory
    private List<FileWithHash> internalFiles;

    private FileWithHash() {

    }

    public String getFileName() {
        return fileName;
    }

    public String getHash() {
        return hash;
    }

    public List<FileWithHash> getInternalFiles() {
        return internalFiles;
    }

    public static Builder builder() {
        return new FileWithHash().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setFileName(String fileName) {
            FileWithHash.this.fileName = fileName;
            return this;
        }

        public Builder setHash(String hash) {
            FileWithHash.this.hash = hash;
            return this;
        }

        public Builder setInternalFiles(List<FileWithHash> internalFiles) {
            FileWithHash.this.internalFiles = internalFiles;
            return this;
        }

        public FileWithHash build() {
            return FileWithHash.this;
        }
    }

    @Override
    public String toString() {
        return "FileWithHash{" +
                "fileName='" + fileName + '\'' +
                ", hash='" + hash + '\'' +
                ", internalFiles=" + internalFiles +
                '}';
    }
}
