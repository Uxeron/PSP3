package com.my.junit.UserData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import com.my.junit.EmailValidator;
import com.my.junit.PasswordChecker;
import com.my.junit.PhoneValidator;
import com.my.junit.Database.FileDatabase;

public class UserRepository {
    private PhoneValidator phoneValidator;
    private EmailValidator emailValidator;
    private PasswordChecker passwordChecker;
    private FileDatabase<User> userDatabase;

    public UserRepository() throws Exception {
        emailValidator = new EmailValidator();
        passwordChecker = new PasswordChecker();
        phoneValidator = new PhoneValidator();
        userDatabase = new FileDatabase<User>("DB.txt");
    }


    public User GetUser(UUID id) {
        return userDatabase.Get(id);
    }

    public ArrayList<String> AddUser(User user) throws Exception {
        ArrayList<String> errors = ValidateUser(user);
        if (errors.size() > 0)
            return errors;

        userDatabase.Add(user);
        return new ArrayList<String>();
    }

    public ArrayList<String> UpdateUser(User user) throws Exception {
        ArrayList<String> errors = ValidateUser(user);
        if (errors.size() > 0)
            return errors;

        userDatabase.Update(user);
        return new ArrayList<String>();
    }

    public ArrayList<String> DeleteUser(UUID id) throws Exception {
        if (!userDatabase.Has(id))
            return new ArrayList<String>( Arrays.asList("User not found.") );

        userDatabase.Remove(id);
        return new ArrayList<String>();
    }


    private ArrayList<String> ValidateUser(User user) {
        ArrayList<String> errorList = new ArrayList<String>();

        char[] symbolsList = { ',', '@', '.', '!' };

        // Check phone number
        if (!phoneValidator.isPhoneNumberValid(user.telefonoNr))
            errorList.add("Phone number is invalid.");

        // Check email
        if (!emailValidator.isEmailValid(user.email))
            errorList.add("Email is invalid.");

        // Check password
        if (!passwordChecker.isPasswordLengthCorrect(user.slaptazodis, 8))
            errorList.add("Password is too short (min 8 symbols)");

        if (!passwordChecker.checkPasswordUpperCase(user.slaptazodis))
            errorList.add("Password is missing uppercase characters");

        if (!passwordChecker.isThereASpecialSymbol(user.slaptazodis, symbolsList))
            errorList.add("Password is missing special characters");

        return errorList;
    }
}
