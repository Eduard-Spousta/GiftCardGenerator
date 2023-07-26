package file;

import java.io.File;
import java.nio.file.Paths;

public interface Editor {
    default String findParentFile(){
        String currentDir = Paths.get(".").toAbsolutePath().normalize().toString();
        String projectDir = new File(currentDir).getParent();
        return projectDir;
    }
    default boolean checkIfExists(File file) {
        return file.exists();
    }

    default File checkForDirectory(){
        //Dynamic path to directory giftCards
        File directory = new File(findParentFile(), "GiftCards");
        if (!checkIfExists(directory))
            directory.mkdir(); //creates directory

        return directory;
    }
}
