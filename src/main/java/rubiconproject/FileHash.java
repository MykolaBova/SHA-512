package rubiconproject;

public class FileHash {
    private String fileName;
    private String hash;

    private FileHash() {

    }

    public String getFileName() {
        return fileName;
    }

    public String getHash() {
        return hash;
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

        public FileHash build() {
            return FileHash.this;
        }
    }

    @Override
    public String toString() {
        return "FileHash{" +
                "fileName='" + fileName + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
