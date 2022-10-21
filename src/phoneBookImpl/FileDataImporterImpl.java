package phoneBookImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import phoneBook.FileDataImporter;
import phoneBook.PhoneNumberValidator;

public final class FileDataImporterImpl implements FileDataImporter<Map<String, String>> {

    private static final String EXCEPTION_MSG_FILE = "File path must not be null or empty!";
    private static final String EXCEPTION_MSG_ARG = "Parameter must not be null!";
    private static final String EXCEPTION_MSG_INVALID_PATH = "Given path is not for file!";

    private static final String DATA_SEPARATOR = " - ";

    private final PhoneNumberValidator phoneNumberValidator;

    public FileDataImporterImpl(final PhoneNumberValidator pPhoneNumberValidator) {
        if (pPhoneNumberValidator != null) {
            phoneNumberValidator = pPhoneNumberValidator;
        } else {
            throw new IllegalArgumentException(EXCEPTION_MSG_ARG);
        }
    }

    @Override
    public void importDataFromFile(String sourcePath, Map<String, String> destination) throws FileNotFoundException {
        if (sourcePath != null && !sourcePath.isEmpty()) {
            File file = new File(sourcePath);
            if (file.isFile()) {
                Scanner sc = new Scanner(file, "UTF-8");

                while (sc.hasNextLine()) {
                    String[] inputLine = sc.nextLine().split(DATA_SEPARATOR);
                    String contactName = inputLine[0];
                    String phoneNumber = inputLine[1];
                    if (!contactName.isEmpty() && phoneNumberValidator.validateNumber(phoneNumber)) {
                        destination.put(contactName, phoneNumberValidator.setValidNumberToNormalForm(phoneNumber));
                    }
                }

                sc.close();
            } else {
                throw new IllegalArgumentException(EXCEPTION_MSG_INVALID_PATH);
            }
        } else {
            throw new IllegalArgumentException(EXCEPTION_MSG_FILE);
        }
    }

}
