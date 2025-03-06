package tokyslav.filereader;

import java.util.ArrayList;
import java.util.List;

import tokyslav.FileTypes;
import tokyslav.Fileobject;

public class filereader {
   
    public filereader(){
        //constructor of class

        
    }

    public static Fileobject[] getInfoFromPath(String p_pathToSearch){
        List<Fileobject> fileobjectlist = new ArrayList<Fileobject>();

        if("1".equals(p_pathToSearch)){
            fileobjectlist.add(new Fileobject("C:\\","8326583", FileTypes.DRIVE));
            fileobjectlist.add(new Fileobject("D:\\","6542345", FileTypes.DRIVE));
            fileobjectlist.add(new Fileobject("E:\\","3949757656", FileTypes.DRIVE));
        }
        if("2".equals(p_pathToSearch)){
            fileobjectlist.add(new Fileobject("Dir_1","28", FileTypes.DIRECTORY));
            fileobjectlist.add(new Fileobject("Dir_2","45", FileTypes.DIRECTORY));
            fileobjectlist.add(new Fileobject("config.xml","64", FileTypes.OTHER));
            fileobjectlist.add(new Fileobject("MeinText.txt","3434", FileTypes.FILE));  
        }
        if("3".equals(p_pathToSearch)){
            fileobjectlist.add(new Fileobject("Dir_1","64792", FileTypes.DIRECTORY));
            fileobjectlist.add(new Fileobject("MeinText.txt","5", FileTypes.FILE));
            fileobjectlist.add(new Fileobject("MeinText2.txt","6482", FileTypes.FILE));
            fileobjectlist.add(new Fileobject("Bachelor.txt","1", FileTypes.FILE));
        }

        return fileobjectlist.toArray(new Fileobject[0]);
    }

}

//Windows: C:\Dev\SizeSeeker_old\src\META-INF
//Mac/Linx: /Dev/SizeSeeker_old/src/

/*  
 * Implementiere in dieser KLasse die Funktionalität aus einem gegebenen Pfad(Windows) alle 
 * darunter liegenden Ordner/Dateien zu lesen und dessen Größe herauszufinden 
 * 
 */

