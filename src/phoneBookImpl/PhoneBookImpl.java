package phoneBookImpl;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import phoneBook.FileDataImporter;
import phoneBook.PhoneBook;
import phoneBook.PhoneNumberValidator;

public final class PhoneBookImpl implements PhoneBook {

    private static final String EXCEPTION_MSG = "Phone register must not be null!";
    private static final String EXCEPTION_MSG_FILE = "Data from file could not be exported due to missing file!";

    private FileDataImporter<Map<String, String>> fileImporter;
    private PhoneNumberValidator validator;

    private final Map<String, String> phoneRegister;
    private final Map<String, Integer> outgoingCalls;

    public PhoneBookImpl(
            final Map<String, String> pPhoneRegister,
            final FileDataImporter<Map<String, String>> pFileImporter,
            final PhoneNumberValidator pValidator) {
        if (pPhoneRegister != null && pFileImporter != null && pValidator != null) {
            phoneRegister = pPhoneRegister;
            fileImporter = pFileImporter;
            validator = pValidator;
        } else {
            throw new IllegalArgumentException(EXCEPTION_MSG);
        }
        outgoingCalls = new HashMap<>();
    }

    @Override
    public boolean importDataFromFile(String filePath) {
        try {
            fileImporter.importDataFromFile(filePath, phoneRegister);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(EXCEPTION_MSG_FILE);
            return false;
        }
    }

    @Override
    public boolean addRecord(String name, String phoneNumber) {
        if (name != null && !name.isEmpty()
                && phoneNumber != null && !phoneNumber.isEmpty()) {
            if (!phoneRegister.containsKey(name) && validator.validateNumber(phoneNumber)) {
                phoneRegister.put(name, validator.setValidNumberToNormalForm(phoneNumber));
                return true;
            }

            System.out.println(name + " is already used!");
        }

        return false;
    }

    @Override
    public boolean deleteRecord(String name) {
        if (name != null && !name.isEmpty()) {
            outgoingCalls.remove(name);
            return phoneRegister.remove(name) != null;
        }

        return false;
    }

    @Override
    public String retrievePhoneNumber(String name) {

        if (name != null && !name.isEmpty()) {
            return phoneRegister.get(name);
        }

        return null;
    }

    @Override
    public void printAllRecords() {
        phoneRegister.entrySet().stream()
                .forEach((entry) -> System.out.println(entry.getKey() + " - " + entry.getValue()));
    }

    @Override
    public void printFiveMostOutgoingNumberPhones() {
        List<Entry<String, Integer>> outgoing = outgoingCalls.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(5)
                .collect(Collectors.toList());

        outgoing.forEach((entry) -> System.out.println(entry.getKey() + " - " + phoneRegister.get(entry.getKey())
                + " - " + entry.getValue()));

    }

    @Override
    public void makeAnOutgoingCall(String name) {
        if (name != null && phoneRegister.containsKey(name)) {
            Integer phoneNumber = outgoingCalls.get(name);
            if (phoneNumber != null) {
                outgoingCalls.put(name, phoneNumber.intValue() + 1);
            } else {
                outgoingCalls.put(name, 1);
            }
        } else {
            System.out.println(name + " does not exists in the register!");
        }
    }
}
