package tokyslav;

public class Fileobject {

    private String filename;
    private long size;
    private FileTypes filetype;

    public Fileobject(String p_filename, long p_size, FileTypes p_filetype) {
        filename = p_filename;
        size = p_size;
        filetype = p_filetype;
    }

    public String getFileName() {
        return filename;
    }

    public long getSize() {
        return size;
    }

    public FileTypes getFileType() {
        return filetype;
    }

}