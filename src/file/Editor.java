package file;

import java.io.File;
import java.nio.file.Paths;

/**
 * Interface for EditorFiles
 */
public interface Editor {
    /**
     * Find Directory in which is Project located
     * @return String path (e.g. /Users/Admin/Documents/.../...)
     */
    default String checkForDirectory(){
        String currentDir = Paths.get(".").toAbsolutePath().normalize().toString();
        return new File(currentDir).getParent();
    }

    /**
     * Check if file is exists
     * @param file File to be checked
     * @return true == do NOT exist
     */
    default boolean checkIfExists(File file) {
        return !file.exists();
    }

    /**
     * Find parent Directory in which is Project located
     * @return String path (e.g. /Users/Admin/Documents/...)
     */
    default File findParentDirectory(){
        //Dynamic path to directory giftCards
        File directory = new File(checkForDirectory(), "GiftCards");
        if (checkIfExists(directory))
            directory.mkdir(); //creates directory

        return directory;
    }
}
