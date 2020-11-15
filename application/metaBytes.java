package application;

import java.io.*;
import java.util.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class metaBytes {

    
    /** Funkcja pathToByte pobiera ścieżkę i zamienia ją w array'a bajtów zmiennej prywmitywnej.
     * @param path ścieżka do zamiany
     * @return byte[] array bajtów zamiany ścieżki
     */
    public static byte[] pathToByte(Path path){
        try {
            byte[] data = Files.readAllBytes(path);
            return data;
        } catch (Exception e){
            System.out.println("Wyjątek:" + e);
            return null;
        }
    }

    
    /** Funkcja stringToByte pobiera stringa w zmiennej prwymitywnej i zamienia go w array'a bajtów zmiennej prywmitywnej
     * @param string string do zamiany
     * @return byte[] array bajtów zamiany stringa
     */
    public static byte[] stringToByte(String string){
        try {
            byte[] data = string.getBytes(StandardCharsets.UTF_8);
            return data;
        } catch (Exception e){
            System.out.println("Wyjątek:" + e);
            return null;
        }
    }
    
    
    /** Funkcja byteReader zamienia arraya bajtów w zmiennej prymitywnej i zamienia go w array lista bajtów w zmiennej kopertowej. Robi to poprzez pętlę iterującą 
     * po elementach @param data i dodaje do zainicjowanego array lista każdy element.
     * @param data array do zamiany
     * @return List<Byte> zamieniony array list
     */
    public static List<Byte> byteReader(byte[] data) {
        try {
            List<Byte> endData = new ArrayList<Byte>();
            for(int i = 0; i<data.length; i++){
                endData.add(data[i]);
            }
            return endData;
        } catch (Exception e) {
            System.out.println("Wyjątek:" + e);
            return null;
        }
    }

    
    /** Funkcja listToArray zamienia array lista bajtów w zmiennej kopertowej na arraya bajtów w zmiennej prymitywnej. Robi to poprzez inicjację arraya o długości odpowiadającej 
     * dlugości @param data i później iterację po tej długości. Przy każdej iteracji dodawany jest kolejny element z array lista do arraya.
     * @param data array list do zamiany
     * @return byte[] zwrócony array
     */
    public static byte[] listToArray(List<Byte> data){
        try{
            byte[] endData = new byte[data.size()];
            for(int i = 0; i<endData.length; i++){
                endData[i] = data.get(i);
            }
            return endData;
        } catch (Exception e) {
            System.out.println("Wyjątek:" + e);
            return null;
        }
    }
    
    
    /** Funckja byteComparator używa biblioteki Collections do wyszukiwania w podanej array liście pewnej podsekwecji. Funkcja zwraca dwuelementowego arraya integerów w zmiennej
     * kopertowej, gdzie na pierwszym miejscu jest początek wyszukanej sekwencji w @param motherSequence a na drugim koniec wyszukanej sekwencji. Jeżeli program nie znajdzie takiej 
     * podsekwencji wtedy na pierwszym miejscu arraya @return Integer[] będzie wartość Integer -1. 
     * @param motherSequence array list porównywany
     * @param userInput array list do wyszukania
     * @return Integer[] zwrócony array granic
     */
    public static Integer[] byteComparator(List<Byte> motherSequence, List<Byte> userInput) {
        try {
            if(motherSequence.size()>=userInput.size()){
                int begin = Collections.indexOfSubList(motherSequence, userInput);
                Integer[] borders = { begin, begin + userInput.size() };
                return borders;
            }else{
                System.out.println("Sublist jest za długi.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Wyjątek:" + e);
            return null;
        }
    }

    
    /** Funckja sequenceSlicer odpowiada za zamianę sekwencji bajtów. Funkcja najpierw tworzy dodatkowego array lista póżniej
     * pobiera @param motherSequence czyli array lista, później za pomocą odpowiednich wartości z @param borders
     * tworzy odpowiednie sublisty i dodaje je do utworzonego na początku arraya. Po wykonaniu pierwszego podziału dodaje do utworzonego na początku array lista @param userInput czyli 
     * ciąg jaki podał użytkownik. Później robi to drugi raz tylko od drugiej wartości @param borders do końca @param motherSequence.
     * @param motherSequence array list do wypełnienia 
     * @param borders granice gdzie mamy włożyć naszego sublista
     * @param userInput array list wartości podanych przez użytkownika.
     * @return List<Byte>
     */
    public static List<Byte> sequenceSlicer(List<Byte> motherSequence, Integer[] borders, List<Byte> userInput){
        try {
            List<Byte> endSequence = new ArrayList<Byte>();
            endSequence.addAll(motherSequence.subList(0, borders[0]));
            endSequence.addAll(userInput);
            endSequence.addAll(motherSequence.subList(borders[1], motherSequence.size()));
            return endSequence;
        } catch (Exception e) {
            System.out.println("Wyjątek:" + e);
            return null;
        }
    }

    
    /** Funkcja byteChanger to funkcja zapisująca w danym pliku zamieniony ciąg bajtów. Najpierw funckcja tworzy trzy array listy: pliku z podanej ścieżki, inputu porównawczego oraz
     * inputu do wsadzenia. Później tworzy arraya integerów w postaci kopertowej z granicami porównawczymi. Jeżeli pierwsze wartość, że jest inna od -1 to tworzy odpowienio zmodyfikowanego
     * array lista i używając FOS nadpisuje podany plik z @param path.
     * @param path ścieżka do pliku
     * @param stringInputOne sekwencja wyszukiwawcza
     * @param stringInputTwo sekwencja do zamiany
     */
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
            System.out.println("Wyjątek:" + e);
        }
    }

    
    /** Funkcja listChanger pobiera array lista ścieżek i iterując po niej zamienia sekwencje w pliku ze ścieżki.
     * @param paths array list ścieżek
     * @param stringInputOne sekwencja do wyszukania w każdy pliku
     * @param stringInputTwo sekwencja do zamiany
     */
    public static void listChanger(List<Path> paths, String stringInputOne, String stringInputTwo){
        try{
            if(paths.size() != 0){
                for(int i = 0; i<paths.size(); i++){
                    byteChanger(paths.get(i), stringInputOne, stringInputTwo);
                }
            }
        } catch (Exception e) {
            System.out.println("Wyjątek:" + e);
        }
    }
}