import java.io.*;
import java.util.*;
import java.lang.Math;
import javax.swing.JFrame;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Main{

    public static byte[] byteReader(Path path) {
        try{
            byte[] data = Files.readAllBytes(path);
            return data;
        }catch (Exception e){
            return null;
        }
    }

    public static byte[] toByteArray(String string) {
        try{
            byte[] converted = string.getBytes(StandardCharsets.UTF_8);
            return converted;
        }catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) {
        
        // JFrame frame = new JFrame(); 
        // frame.setTitle("ByteChanger 2020");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(300,250);
        // frame.setVisible(true);
        Path path1 = Paths.get("C:/Users/najma/Desktop/");
        System.out.println(byteReader(path1));
        int len = byteReader(path1).length;
        System.out.println(len+ "ddd");
        for(int i=0;  i<=len; i++){
            System.out.println(byteReader(path1)[i]);
        }
    }
}