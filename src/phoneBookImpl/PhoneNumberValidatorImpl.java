package phoneBookImpl;

import phoneBook.PhoneNumberValidator;

public final class PhoneNumberValidatorImpl implements PhoneNumberValidator {

    private static final String EXCEPTION_MSG = "Parameter must not be null!";

    private final String regex;
    private final Integer maxNumberLength;

    public PhoneNumberValidatorImpl(final String pRegex, final Integer pMaxNumberLength) {
        if (pRegex != null && !pRegex.isEmpty() && pMaxNumberLength != null && pMaxNumberLength > 0) {
            regex = pRegex;
            maxNumberLength = pMaxNumberLength;
        } else {
            throw new IllegalArgumentException(EXCEPTION_MSG);
        }
    }

    @Override
    public boolean validateNumber(String phoneNumber) {

        if (phoneNumber != null && maxNumberLength.compareTo(phoneNumber.length()) >= 0) {
            return phoneNumber.matches(regex);
        }
        return false;
    }

    @Override
    public String setValidNumberToNormalForm(String number) {
        if (number != null && !number.isEmpty()) {
            return setToNormalForm(number);
        } else {
            throw new IllegalArgumentException(EXCEPTION_MSG);
        }
    }

    private String setToNormalForm(final String phoneNumber) {
        int size = phoneNumber.length();
        StringBuilder sb = new StringBuilder(phoneNumber);

        if (size == 10) {
            sb.replace(0, 1, "+359");
        } else if (size == 14) {
            sb.replace(0, 2, "+");
        }

        return sb.toString();
    }
}
