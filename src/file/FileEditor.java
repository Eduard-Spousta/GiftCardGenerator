package file;

import model.Data;

import java.io.*;

public class FileEditor implements Editor {
    public FileEditor(Data data) {
        try {
            //File ListGiftCards
            File file = new File(checkForDirectory(), "ListGiftCards.csv");
            if (!checkIfExists(file)) {
                try {
                    //ADD NAME OF COLUMNS
                    FileWriter fileWriter = new FileWriter(file, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("KOD" + "," + "PLATNOST DO" + "," + "CENA" + "," + "UPLATNENO");
                    bufferedWriter.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            //Safe File
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write(data.getVerificationCode() + "," + data.getExpirationDate() + "," + data.getPrice() + "," + "Ne");
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException("Error while saving .csv File. Please contact Admin." + e);
        }
    }
}
