import java.io.*;
import java.util.*;
import java.lang.Math;
import javax.swing.JFrame;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Main {

    

    public static byte[] pathToByte(Path path){
        try {
            byte[] data = Files.readAllBytes(path);
            return data;
        } catch (Exception e){
            return null;
        }
    }

    public static byte[] stringToByte(String string){
        try {
            byte[] data = string.getBytes(StandardCharsets.UTF_8);
            return data;
        } catch (Exception e){
            return null;
        }
    }
    public static List<Byte> byteReader(Path path) {
        try {
            byte[] data = Files.readAllBytes(path);
            List<Byte> endData = new ArrayList<Byte>();
            for(int i = 0; i<data.length; i++){
                endData.add(data[i]);
            }
            return endData;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Integer[] byteComparator(List<Byte> motherSequence, List<Byte> userInput) {
        try {
            int begin = Collections.indexOfSubList(motherSequence, userInput);
            System.out.println(begin);
            Integer[] borders = { begin, begin + userInput.size() };
            return borders;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {

        Path path1 = Paths.get("C:/Users/najma/Desktop/test.txt");
        Path path2 = Paths.get("C:/Users/najma/Desktop/test2.txt");
        Integer[] ends = byteComparator(byteReader(path1), byteReader(path2));
        for (int i = 0; i < ends.length; i++) {
            System.out.println(ends[i]);
        }
    }
}