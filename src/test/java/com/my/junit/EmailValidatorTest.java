package com.my.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmailValidatorTest {
    private EmailValidator emailValidator;

    @BeforeEach
    public void Setup() {
        emailValidator = new EmailValidator();
    }

    @Test
    public void TestNullEmail(){
        Assertions.assertFalse(emailValidator.isEmailValid(null));
    }
    @Test
    public void TestEmptyEmail(){
        String email = "";
        Assertions.assertFalse(emailValidator.isEmailValid(email));
    }

    @Test
    public void TestEmailHasNoEta(){
        String email = "jonaitis.mail.com";
        Assertions.assertFalse(emailValidator.isEmailValid(email));
    }
    @Test
    public void TestHasBadDomain(){
        String email = "jonaitis@gmail.9";
        Assertions.assertFalse(emailValidator.isEmailValid(email));
    }

    @Test
    public void TestHasBadTopLevelDomain(){
        String email = "jonaitis@.com";
        Assertions.assertFalse(emailValidator.isEmailValid(email));
    }

    @Test
    public void TestPrefixEndsWithDash(){
        String email = "jonaitis-@mail.com";
        Assertions.assertFalse(emailValidator.isEmailValid(email));
    }
    @Test
    public void TestPrefixPeriodIsNotFollowedByLetter(){
        String email = "jonaitis..def@mail.com";
        Assertions.assertFalse(emailValidator.isEmailValid(email));

    }
    @Test
    public void TestPrefixStartsWithPeriod(){
        String email = ".jonaitis@mail.com";
        Assertions.assertFalse(emailValidator.isEmailValid(email));
    }
    @Test
    public void TestPrefixHasUnusedSymbol(){
        String email = "jonaitis~def@mail.com";
        Assertions.assertFalse(emailValidator.isEmailValid(email));
    }
    @Test
    public void TestCorrectPrefixWithDash(){
        String email = "Jonaitis-d@mail.com";
        Assertions.assertTrue(emailValidator.isEmailValid(email));
    }
    @Test
    public void TestCorrectPrefixWithUnderScore(){
        String email= "jonaitis_def@mail.com";
        Assertions.assertTrue(emailValidator.isEmailValid(email));
    }
    @Test
    public void TestLastPortionOfDomainHasOnlyOneLetter(){
        String email = "jonaitis_def@mail.c";
        Assertions.assertTrue(emailValidator.isEmailValid(email));
    }
    @Test
    public void TestValidEmail(){
        String email = " jonas.jonaitis@mail-archive.com";
        Assertions.assertTrue(emailValidator.isEmailValid(email));
    }
}

//Gretos Murnevaites testai
 /*

class EmailValidatorTest {

    EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
    }

    //Test checks if the email has special symbol
    @Test
    void EmailValidator_EmailHasSpecialSymbol(){
        assertTrue(emailValidator.specialSymbolRequirement("admin@gmail.com"));
    }

    //Test checks for invalid characters
    @Test
    void EmailValidator_NoUnsupportedCharacters(){
        assertTrue(emailValidator.supportedCharactersRequirement("admin@gmail.com"));
    }

    @Test
    void EmailValidator_HasUnsupportedCharacters(){
        assertFalse(emailValidator.supportedCharactersRequirement("a¿dmin@gmail.com"));
    }

    //Test verifies that the mail has the correct domain and TLD
    @Test
    void EmailValidator_CorrectDomain(){
        assertTrue(emailValidator.domainRequirement("admin@gmail.com"));
    }

    @Test
    void EmailValidator_WrongDomain(){
        assertFalse(emailValidator.domainRequirement("admin@email.1"));
    }

    @Test
    void EmailValidator_WrongTLD(){
        assertFalse(emailValidator.domainRequirement("admin@.lt"));
    }
}

*/