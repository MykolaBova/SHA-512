package rubiconproject;

import java.util.List;

/**
 * Result of calculation hash.
 * Contains {@link String} fileName,
 *          {@link String} hash and
 *          {@link List} of {@link FileHash} internal objects for directories
 */
public class FileHash {

    // Name of file or directory
    private String fileName;

    // Hash for file or directory
    private String hash;

    // List of internal objects (files or directories) if this is directory
    private List<FileHash> internalFiles;

    private FileHash() {

    }

    public String getFileName() {
        return fileName;
    }

    public String getHash() {
        return hash;
    }

    public List<FileHash> getInternalFiles() {
        return internalFiles;
    }

    public static Builder builder() {
        return new FileHash().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setFileName(String fileName) {
            FileHash.this.fileName = fileName;
            return this;
        }

        public Builder setHash(String hash) {
            FileHash.this.hash = hash;
            return this;
        }

        public Builder setInternalFiles(List<FileHash> internalFiles) {
            FileHash.this.internalFiles = internalFiles;
            return this;
        }

        public FileHash build() {
            return FileHash.this;
        }
    }

    @Override
    public String toString() {
        return "FileHash{" +
                "fileName='" + fileName + '\'' +
                ", hash='" + hash + '\'' +
                ", internalFiles=" + internalFiles +
                '}';
    }
}
