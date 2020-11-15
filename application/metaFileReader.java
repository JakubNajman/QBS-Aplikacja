package application;

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class metaFileReader {

    
    /** Funckja parsująca rozszerzenie z naszego pliku. 
     * @param fileName nazwa pliku wraz z rozszerzeniem
     * @return String sparsowane rozszerzenie pliku
     */
    public static String getFileExtension(String fileName) {
        String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
        return tokens[1];
    }

    
    /** Funkcja rekurencyjna, która dodaje do array lista ścieżek @param filePaths ścieżkę do pliku jeżeli zgadza się podane rozszerzenie @param fileExtension z danej ścieżki @param path
     * Funkcja ta najpier sprawdza, czy plik z danej ściezki istnieje lub jest katalogiem. Jeżeli tak to inicjuje array lista wszystkich podścieżek jakie są w tym katalogu. Poźniej sprawdza
     * czy @param index jest równy wielkości array listy (jeżeli jest równy zero to wtedy nasz katalog jest już cały sprawdzony). Później jeżeli wartość nazej tabeli od @param index 
     * może być albo plikiem albo katalogiem. Jeżeli jest plikiem to dodajemy ścieżkę do tego pliku do @param filePaths. Jeżeli jest to katalog to wchodzimy do tego katalogu i wywołujemy
     * znowu naszą funkcję. Jeżeli nie trafimy na katalog to znowu wykonujemy naszą funkcję i inkrementujemy @param index.
     * @param path
     * @param fileExtension
     * @param index
     * @param filePaths
     */
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
                    if(tempString.equals(fileExtension)){
                        Path temp = Paths.get(listOfFiles.get(index).getCanonicalPath());
                        filePaths.add(temp);
                    }
                } else
                if(listOfFiles.get(index).isDirectory()){
                    Path temp = Paths.get(listOfFiles.get(index).getCanonicalPath());
                    recDirectoryReader(temp, fileExtension, 0, filePaths);
                }

                recDirectoryReader(path, fileExtension, ++index, filePaths);
            }
            
        } catch (Exception e) {
            System.out.println("Wyjątek:" + e);
        }
    }

}
