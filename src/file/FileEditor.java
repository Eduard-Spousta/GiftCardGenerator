package file;

import model.Data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileEditor {
    //TODO: SOME SORT OF TABLE TO MANAGE CODES - (expirationDate, dode, dateOfCreation, Price)

    public FileEditor(Data data) {
        try {
            String currentDir = Paths.get(".").toAbsolutePath().normalize().toString();
            String projectDir = new File(currentDir).getParent();

            //Dynamic path to directory giftCards
            File directory = new File(projectDir, "GiftCards");
            if (!checkIfExists(directory))
                directory.mkdir(); //creates directory

            //Dynamic path to ListGiftCards
            File file = new File(directory, "ListGiftCards.csv");
            if (!checkIfExists(file))
                file.createNewFile();

            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data.getVerificationCode() + "," + data.getExpirationDate() + "," + data.getPrice());
            bufferedWriter.newLine();
            bufferedWriter.close();

            System.out.println(bufferedWriter.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkIfExists(File file) {
        return file.exists();
    }
}
