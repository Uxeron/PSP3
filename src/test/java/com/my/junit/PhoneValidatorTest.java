package com.my.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneValidatorTest {
    private PhoneValidator phoneValidator;


    @BeforeEach
    public void SetUp(){
        phoneValidator = new PhoneValidator();
    }
    @Test
    public void TestNoSpaceBarsInPhoneNumber(){
        String number = "+370 343 00 000";
        Assertions.assertFalse(phoneValidator.isPhoneNumberValid(number));
    }
    @Test
    public void TestNoOtherSymbolsInPhoneNumber(){
        String number = "(86) 21 380 62";
        Assertions.assertFalse(phoneValidator.isPhoneNumberValid(number));
    }
    @Test
    public void TestPhoneNumberIsShorterByOne(){
        String number = "+3706213906";
        Assertions.assertFalse(phoneValidator.isPhoneNumberValid(number));
    }
    @Test
    public void TestPhoneNumberIsLongerByTwo(){
        String number = "+3706217806238";
        Assertions.assertFalse(phoneValidator.isPhoneNumberValid(number));    }
    @Test
    public void TestEmptyPhoneNumber(){
        String number = "    ";
        Assertions.assertFalse(phoneValidator.isPhoneNumberValid(number));    }
    @Test
    public void TestNullNumber(){
        Assertions.assertFalse(phoneValidator.isPhoneNumberValid(null));
    }
    @Test
    //
    public void TestPhoneNumberStartsWithLetter(){
        String number = "c62138079";
        Assertions.assertFalse(phoneValidator.isPhoneNumberValid(number));
    }
    @Test
    public void TestPhoneNumberStartsWithIncorrectSymbol(){
        String number = "-37062157469";
        Assertions.assertFalse(phoneValidator.isPhoneNumberValid(number));
    }
    @Test
    public void TestPhoneNumberReplacesPrefix(){
        String number = "869721384";
        Assertions.assertEquals("+37069721384", phoneValidator.replacePhoneNumberPrefix(number));
    }
    @ParameterizedTest
    @ValueSource(strings = {"+37062138062", "862138062","+37062891542","862891542"})
    public void TestIsNumberValidCorrect(String input){
        assertTrue(phoneValidator.isPhoneNumberValid(input));
    }

    @Test
    public void TestIsAnotherStateNumberIsValid(){
        String number = "+38760123456";
        Assertions.assertTrue(phoneValidator.isPhoneNumberValidByState(number,"+387","60",8));
    }
}

//Gretos Murnevaites testai
/*
class PhoneValidatorTest {

    PhoneValidator phoneValidator;

    @BeforeEach
    void setUp() {
        phoneValidator = new PhoneValidator();
    }

    //Test checks for characters other than numbers
    @Test
    void PhoneValidator_NoCharactersOtherThanNumbers(){
        assertTrue(phoneValidator.numbersRequirement("+37061234567"));
    }

    //Test checks for new validations
    @Test
    void PhoneValidator_OtherCountryPhoneNumberPrefixCorrect(){
        assertEquals("The phone prefix is correct", phoneValidator.prefixRequirement("LT", "+37061234578"));
    }

    @Test
    void PhoneValidator_OtherCountryPhoneNumberLengthCorrect(){
        assertEquals("The phone number length is correct", phoneValidator.lengthRequirement("LT", "+37061234578"));
    }

    @Test
    void PhoneValidator_OtherCountryPhoneNumberPrefixWrong(){
        assertEquals("The phone prefix does not match country code", phoneValidator.lenghtRequirement("PL", "+37061234578"));
    }

    @Test
    void PhoneValidator_OtherCountryPhoneNumberLengthWrong(){
        assertEquals("The phone number is too long", phoneValidator.prefixRequirement("PL", "+370612345787878787"));
    }
}

 */