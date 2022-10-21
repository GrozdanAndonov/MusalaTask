package demo;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import phoneBook.FileDataImporter;
import phoneBook.PhoneBook;
import phoneBook.PhoneNumberValidator;
import phoneBookImpl.FileDataImporterImpl;
import phoneBookImpl.PhoneBookImpl;
import phoneBookImpl.PhoneNumberValidatorImpl;

public final class Demo {

	private static final Integer NUMBER_MAX_LENGTH = 14;
	private static final String REGEX = "(\\+|00)?(359|0)8[789][2-9]\\d{6}";
	private static final String FILE_PATH = "." + File.separator + "testFiles" + File.separator + "test.txt";
	
	public static void main(String[] args) {
		PhoneNumberValidator phoneValidator = new PhoneNumberValidatorImpl(REGEX, NUMBER_MAX_LENGTH);
		FileDataImporter<Map<String, String>> fileImporter = new FileDataImporterImpl(phoneValidator);
		PhoneBook phoneBook = new PhoneBookImpl(new TreeMap<>(), fileImporter, phoneValidator);
		
		phoneBook.importDataFromFile(FILE_PATH);
		phoneBook.printAllRecords();
		System.out.println();
		phoneBook.makeAnOutgoingCall("Jack Daniels");
		phoneBook.makeAnOutgoingCall("Богдан Георгиев");
		phoneBook.makeAnOutgoingCall("Jack Daniels");
		phoneBook.makeAnOutgoingCall("Иван Шишман");
		phoneBook.makeAnOutgoingCall("sgsgsgsdgsdf");
		System.out.println();
		phoneBook.printFiveMostOutgoingNumberPhones();
	}
}