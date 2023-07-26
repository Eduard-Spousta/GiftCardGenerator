package file;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileEditor {
    //TODO: SOME SORT OF TABLE TO MANAGE CODES - (expirationDate, dode, dateOfCreation, Price)



    public FileEditor(){
        try {
            File file = new File("ListGiftCards.txt");
            if(!file.exists()){
                FileWriter fileWriter = new FileWriter("ListGiftCards.txt");
            }
            List<String> rows = new ArrayList<>();
            rows = Files.readAllLines(Path.of("ListGiftCards.txt"));
            System.out.println(rows);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
