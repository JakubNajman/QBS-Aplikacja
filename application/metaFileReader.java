package application;

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class metaFileReader {

    public static String getFileExtension(String fileName) {
        String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
        return tokens[1];
    }

    public static void recDirectoryReader(Path path, String fileExtension, int index, List<Path> filePaths){
        try {
            File mainDir = new File(path.toString());
            
            if(mainDir.exists() && mainDir.isDirectory()){
                List<File> listOfFiles = Arrays.asList(mainDir.listFiles());
                if(index == listOfFiles.size()){
                    return;
                }

                if(listOfFiles.get(index).isFile()){
                    String tempString = getFileExtension(listOfFiles.get(index).toString());
                    System.out.println(tempString);
                    System.out.println(fileExtension);
                    System.out.println("Next file");
                    if(tempString.equals(fileExtension)){
                        Path temp = Paths.get(listOfFiles.get(index).getCanonicalPath());
                        filePaths.add(temp);
                        System.out.println("File: " + listOfFiles.get(index).getName());
                    }
                } else
                if(listOfFiles.get(index).isDirectory()){
                    System.out.println("Dir:" + listOfFiles.get(index).getName());
                    Path temp = Paths.get(listOfFiles.get(index).getCanonicalPath());
                    recDirectoryReader(temp, fileExtension, 0, filePaths);
                }

                recDirectoryReader(path, fileExtension, ++index, filePaths);
            }
            
        } catch (Exception e) {
            
        }
    }

    public static void main(String[] args) {
        Path temp = Paths.get("C:/Users/najma/Desktop/Test/");
        List<Path> filePaths = new ArrayList<Path>();
    }
}
