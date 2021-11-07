package com.my.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PasswordCheckerTest {

    private PasswordChecker passwordChecker;

    @BeforeEach
    public void SetUp(){
        passwordChecker = new PasswordChecker();
    }

    @Test
    public void TestPasswordLengthIsCorrect(){
        String password = "jOnaitis519*";
        int length = 8;
        Assertions.assertTrue(passwordChecker.isPasswordLengthCorrect(password,length));
    }
    @Test
    public void TestPasswordLengthIsShorterThanRequired(){
        String password = "Mika.45";
        int length = 8;
        Assertions.assertFalse(passwordChecker.isPasswordLengthCorrect(password,length));
    }

    @Test
    public void TestThereIsNoUpperCaseInPassword(){
        String password = "nikosija45-";
        Assertions.assertFalse(passwordChecker.checkPasswordUpperCase(password));
    }

    @Test
    public void TestThereIsUpperCaseInPassword(){
        String password = "nikoSija45-";
        Assertions.assertTrue(passwordChecker.checkPasswordUpperCase(password));
    }
    @Test
    public void TestSpecialSymbol(){
        String password = "nikaRagvasijus15@12";
        char[] symbolsList= {',','@','.','!'};
        Assertions.assertTrue(passwordChecker.isThereASpecialSymbol(password,symbolsList));
    }
    @Test
    public void TestSpecialSymbolFalse(){
        String password = "nikaRagvasijus15}12";
        char[] symbolsList= {',','@','.','!'};
        Assertions.assertFalse(passwordChecker.isThereASpecialSymbol(password,symbolsList));
    }

    @Test
    public void TestIsPasswordValidTrue(){
        String password = "12345678A-";
        Assertions.assertTrue(passwordChecker.isPasswordValid(password));
    }

    @Test
    public void TestIsPasswordValidFalse(){
        String password = "antanas12";
        Assertions.assertFalse(passwordChecker.isPasswordValid(password));
    }

    @Test
    public void TestPasswordEmpty(){
        String password = "";
        Assertions.assertFalse(passwordChecker.isPasswordValid(password));
    }

    @Test
    public void TestPasswordNull() {
        Assertions.assertFalse(passwordChecker.isPasswordValid(null));
    }
}

//Gretos Murnevaites testai
/*
class PasswordCheckerTest {

    PasswordChecker passwordChecker;

    @BeforeEach
    void setUp() {
        passwordChecker = new PasswordChecker();
    }

    //Test checks the length of the password
    @Test
    void PasswordChecker_PasswordTooShort(){
        assertFalse(passwordChecker.lenghtRequirement("asdfgh", 9));
    }

    @Test
    void PasswordChecker_PasswordLengthIsCorrect(){
        assertTrue(passwordChecker.lenghtRequirement("qwertyuio", 9));
    }

    @Test
    void PasswordChecker_PasswordNotEntered(){
        assertFalse(passwordChecker.lenghtRequirement("", 9));
    }

    //Test checks for uppercase characters
    @Test
    void PasswordChecker_PasswordContainsUppercaseCharacters(){
        assertTrue(passwordChecker.uppercaseCharactersRequirement("Adminpass"));
    }

    @Test
    void PasswordChecker_PasswordNotContainsUppercaseCharacters(){
        assertFalse(passwordChecker.uppercaseCharactersRequirement("adminpass"));
    }

    //Test checks for a special character
    @Test
    void PasswordChecker_PasswordContainsSpecialCharacter(){
        assertTrue(passwordChecker.specialCharacterRequirement("admin-123"));
    }

    @Test
    void PasswordChecker_PasswordNotContainsSpecialCharacter(){
        assertFalse(passwordChecker.specialCharacterRequirement("adminn123"));
    }
}*/