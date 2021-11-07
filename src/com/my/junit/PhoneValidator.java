package com.my.junit;

public class PhoneValidator {

    public boolean isPhoneNumberValid(String number) {

        if(null != number) if (!number.isEmpty())
            return noSpaceBars(number) && onlyNumbers(number) && lengthIsRight(number);
        return false;
    }

    private boolean lengthIsRight(String number) {
        return number.length() == 12 || number.length() == 9;
    }

    private boolean noSpaceBars(String number) {

        return !number.contains(" ");
    }

    private boolean onlyNumbers(String number) {
        int a = 0;
        if(number.charAt(0) == '+') a = 1;
        for(int i = a; i < number.length(); i++)
            return number.charAt(i) >= '0' && number.charAt(i) <= '9';
        return false;
    }

    public String replacePhoneNumberPrefix(String number) {
        String newPhoneNumber;
        newPhoneNumber = number.replaceFirst("8", "+370");
        return newPhoneNumber;
    }

    public boolean isPhoneNumberValidByState(String number, String prefix, String localPrefix, int length) {
        String allPrefix = prefix + localPrefix;
        if(number.startsWith(allPrefix)) return number.length() - allPrefix.length() == length - 2;
        return false;
    }
}
