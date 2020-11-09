package application;

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class metaMain {
    
    public static void main(String[] args) {
        List<Path> temp = new ArrayList<Path>();
        Path test = Paths.get("C:/Users/najma/Desktop/Test");
        metaFileReader.recDirectoryReader(test, "txt", 0, temp);
        metaBytes.listChanger(temp, "test", "dziodziooo");
    }
}
