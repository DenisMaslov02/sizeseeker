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
        fileobjectlist.add(new Fileobject("Dir_1","3", FileTypes.DIRECTORY));
        fileobjectlist.add(new Fileobject("Dir_2","6", FileTypes.OTHER));
        fileobjectlist.add(new Fileobject("Dir_3","3434", FileTypes.FILE));
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

