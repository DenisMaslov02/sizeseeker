package tokyslav.filereader;

import java.util.*;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class filereader {

    @SuppressWarnings("unchecked")
    public static <Interger> void getFileNames() throws IOException {

        List myDataList = new LinkedList<Interger>();
        List myDataTyp = new LinkedList<Interger>();
        List myDataSize = new LinkedList<Interger>();

        File root = new File("C:\\");
        File[] folderRoot = root.listFiles();

        File path = new File("\\");
        File[] folderSizeList = path.listFiles();

        if (null != root) {
            for (int folderIntList = 0; folderIntList < folderRoot.length; folderIntList++) {

                String folder = folderRoot[folderIntList].toString();
                String fileExt = Files.getFileExtension(folderRoot[folderIntList].toString());

                Path pathsize = Paths.get(folderSizeList[folderIntList].toString());
                FileChannel fileChannel;
                // Path folderPath = Paths.get(folder[folderIntList].toString);
                // long sizefile = Files.size(folderRoot[folderIntList]);
                if (null != folder && folder.length() > 0) {
                    myDataList.add(folder.substring(folder.lastIndexOf("\\") + 1, folder.length()));
                    myDataTyp.add(fileExt);
                    // try {
                    // fileChannel = FileChannel.open(pathsize);
                    // long filesize = fileChannel.size();
                    // System.out.println(filesize + "bytes");
                    // fileChannel.close();
                    // } catch (IOException e) {
                    // e.printStackTrace();
                    // }
                }
                // myDataSize.add(pathsize.size());
                // System.out.println("File:" + (folderIntList + 1) + ": "
                // + folder.substring(pathsize.lastIndexOf("\\") + 1, pathsize.length()));
                // for print!!! Don´t delete
            }
        }
        // System.out.println(myDataList);
        // System.out.println(myDataTyp);
        // System.out.println(myDataSize);
    }
}

// Windows: C:\Dev\SizeSeeker_old\src\META-INF
// Mac/Linx: /Dev/SizeSeeker_old/src/

/*
 * Implementiere in dieser KLasse die Funktionalität aus einem gegebenen
 * Pfad(Windows) alle
 * darunter liegenden Ordner/Dateien zu lesen und dessen Größe herauszufinden
 * 
 */

// TODO exists() für search folder/data

// SystemDATA: 1,6,8,9,10,11,14,16,21,23,24

// 16 ist das Gleiche wie 18