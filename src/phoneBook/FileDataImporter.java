package phoneBook;

import java.io.FileNotFoundException;

public interface FileDataImporter<T> {

    void importDataFromFile(String sourcePath, T destination)
            throws FileNotFoundException;

}
