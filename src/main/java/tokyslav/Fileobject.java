package tokyslav;

public class Fileobject {

    private String filename;
    private int size;
    private FileTypes filetype;

    public Fileobject(String p_filename, int p_size, FileTypes p_filetype) {
        filename = p_filename;
        size = p_size;
        filetype = p_filetype;
    }

    public String getFileName() {
        return filename;
    }

    public int getSize() {
        return size;
    }

    public FileTypes getFileType() {
        return filetype;
    }

}