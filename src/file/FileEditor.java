package file;

import model.Data;

import java.io.*;

/**
 * Works with .CSV file
 */
public class FileEditor implements Editor {

    /**
     * Edit/Create new CSV file with given data
     *
     * @param data Data given by user
     */
    public FileEditor(Data data) {
        try {
            //File ListGiftCards
            File file = new File(findParentDirectory(), "ListGiftCards.csv");
            if (checkIfExists(file)) {
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
            bufferedWriter.write(data.verificationCode() + "," + data.expirationDate() + "," + data.price() + "," + "Ne");
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException("Error while saving .csv File. Please contact Admin." + e);
        }
    }
}
