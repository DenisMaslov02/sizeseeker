package tokyslav;

public class Fileobject {
    
    private String filename;
    private String size;
    private FileTypes filetype = FileTypes.DIRECTORY;

    public Fileobject(String p_filename, String p_size, FileTypes p_filetype){
        filename = p_filename;
        size = p_size;
        filetype = p_filetype;
    }


}