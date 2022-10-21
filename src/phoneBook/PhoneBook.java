package phoneBook;

public interface PhoneBook {

    boolean importDataFromFile(String filePath);

    boolean addRecord(String name, String phoneNumber);

    boolean deleteRecord(String name);

    String retrievePhoneNumber(String name);

    void printAllRecords();

    void printFiveMostOutgoingNumberPhones();

    void makeAnOutgoingCall(String name);

}
