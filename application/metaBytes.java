package application;

import java.io.*;
import java.util.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class metaBytes {

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
    public static List<Byte> byteReader(byte[] data) {
        try {
            List<Byte> endData = new ArrayList<Byte>();
            for(int i = 0; i<data.length; i++){
                endData.add(data[i]);
            }
            return endData;
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] listToArray(List<Byte> data){
        try{
            byte[] endData = new byte[data.size()];
            for(int i = 0; i<endData.length; i++){
                endData[i] = data.get(i);
            }
            return endData;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Integer[] byteComparator(List<Byte> motherSequence, List<Byte> userInput) {
        try {
            int begin = Collections.indexOfSubList(motherSequence, userInput);
            Integer[] borders = { begin, begin + userInput.size() };
            return borders;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Byte> sequenceSlicer(List<Byte> motherSequence, Integer[] borders, List<Byte> userInput){
        try {
            List<Byte> endSequence = new ArrayList<Byte>();
            endSequence.addAll(motherSequence.subList(0, borders[0]));
            endSequence.addAll(userInput);
            endSequence.addAll(motherSequence.subList(borders[1], motherSequence.size()));
            return endSequence;
        } catch (Exception e) {
            return null;
        }
    }

    public static void byteChanger(Path path, String stringInputOne, String stringInputTwo){
        try {
            List<Byte> motherSequence = byteReader(pathToByte(path));
            List<Byte> userInputOne = byteReader(stringToByte(stringInputOne));
            List<Byte> userInputTwo = byteReader(stringToByte(stringInputTwo));
            Integer[] borders = byteComparator(motherSequence, userInputOne);
            if(borders[0] != -1){
                List<Byte> changedMother = sequenceSlicer(motherSequence, borders, userInputTwo);
                byte[] motherArray = listToArray(changedMother);
                try (FileOutputStream fos = new FileOutputStream(path.toString())) {
                    fos.write(motherArray);
                }
            }
        } catch (Exception e) {
            System.out.println("null");
        }
    }

    public static void listChanger(List<Path> paths, String stringInputOne, String stringInputTwo){
        try{
            if(paths.size() != 0){
                for(int i = 0; i<paths.size(); i++){
                    byteChanger(paths.get(i), stringInputOne, stringInputTwo);
                }
            }
        } catch (Exception e) {
            System.out.println("null");
        }
    }

    public static void main(String[] args) {

        Path path1 = Paths.get("C:/Users/najma/Desktop/test.txt");
        byteChanger(path1, "fishfish", "DziałaDziała");
    }
}