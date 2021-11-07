package com.my.junit;

public class PasswordChecker {

    public boolean isPasswordLengthCorrect(String password, int length) {
        return password.length() >= length;
    }

    public boolean checkPasswordUpperCase(String password) {
        char letter;
        boolean correct = false;

        for(int i = 0; i < password.length(); i++) {
            letter = password.charAt(i);
            if (Character.isUpperCase(letter)) correct = true;
            if (correct) return true;
        }
        return false;
    }

    public boolean isThereASpecialSymbol(String password, char[] list) {

        for(char c : list) {
            for (int j = 0; j < password.length(); j++) {
                if (password.charAt(j) == c) return true;
            }
        }
        return false;
    }

    public boolean isPasswordValid(String password) {

        if(password != null) return !password.isEmpty() && checkPasswordUpperCase(password);
        else return false;
    }
}
