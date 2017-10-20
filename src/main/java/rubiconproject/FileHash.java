package rubiconproject;

import java.math.BigDecimal;
import java.util.List;

public class FileHash {
    private String fileName;
    private String hash;
    private boolean directory;
    private List<FileHash> internalFiles;

    private FileHash() {

    }

    public String getFileName() {
        return fileName;
    }

    public String getHash() {
        return hash;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public boolean isDirectory() {
        return directory;
    }

    public List<FileHash> getInternalFiles() {
        return internalFiles;
    }

    public void setInternalFiles(List<FileHash> internalFiles) {
        this.internalFiles = internalFiles;
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

        public Builder setDirectory(boolean directory) {
            FileHash.this.directory = directory;
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
                ", directory=" + directory +
                ", internalFiles=" + internalFiles +
                '}';
    }
}
