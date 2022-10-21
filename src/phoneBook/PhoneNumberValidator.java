package phoneBook;

public interface PhoneNumberValidator {

	/**
	 * Returns boolean value "true" if the passed as parameter phone number is valid
	 * and "false" if it is not valid.
	 * 
	 * @param phoneNumber String
	 * @return boolean
	 */
	boolean validateNumber(String phoneNumber);
	
	String setValidNumberToNormalForm(String number);

}
