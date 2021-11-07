package com.my.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.UUID;

import com.my.junit.Database.FileDatabase;
import com.my.junit.UserData.User;
import com.my.junit.UserData.UserRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {
    private static PhoneValidator phoneValidator;
    private static EmailValidator emailValidator;
    private static PasswordChecker passwordChecker;
    private static FileDatabase<User> userDatabase;

    private static UUID nullUserUUID = UUID.randomUUID();
    private static UUID goodUserUUID = UUID.randomUUID();

    private static User goodUser;

    private static UserRepository userRepository;

    @BeforeAll
    public static void setup() {
        phoneValidator = mock(PhoneValidator.class);
        //when(phoneValidator.isPhoneNumberValid(anyString())).thenReturn(true);

        emailValidator = mock(EmailValidator.class);
        when(emailValidator.isEmailValid(anyString())).thenReturn(true);

        passwordChecker = mock(PasswordChecker.class);
        when(passwordChecker.isPasswordLengthCorrect(anyString(), anyInt())).thenReturn(true);
        when(passwordChecker.checkPasswordUpperCase(anyString())).thenReturn(true);
        when(passwordChecker.isThereASpecialSymbol(anyString(), any(char[].class))).thenReturn(true);

        goodUser = new User("name", "surname", "telnr", "email", "addr", "slapt", goodUserUUID);

        userDatabase = (FileDatabase<User>) mock(FileDatabase.class);
        when(userDatabase.Get(nullUserUUID)).thenReturn(null);
        when(userDatabase.Get(goodUserUUID)).thenReturn(goodUser);
        when(userDatabase.Has(nullUserUUID)).thenReturn(false);
        when(userDatabase.Has(goodUserUUID)).thenReturn(true);

        userRepository = new UserRepository(phoneValidator, emailValidator, passwordChecker, userDatabase);
    }


    @Test
    public void GetUserInvalid() throws Exception {
        // Arrange

        // Act
        User user = userRepository.GetUser(nullUserUUID);

        // Assert
        assertEquals(null, user);
    }

    @Test
    public void GetUserValid() throws Exception {
        // Arrange

        // Act
        User user = userRepository.GetUser(goodUserUUID);

        // Assert
        assertEquals(goodUser, user);
    }

    @Test
    public void AddUserInvalid() throws Exception {
        // Arrange
        when(phoneValidator.isPhoneNumberValid(anyString())).thenReturn(false);

        // Act
        ArrayList<String> errors = userRepository.AddUser(goodUser);

        // Assert
        assertTrue(errors.size() == 1);
        assertEquals("Phone number is invalid.", errors.get(0));
    }

    @Test
    public void AddUserValid() throws Exception {
        // Arrange
        when(phoneValidator.isPhoneNumberValid(anyString())).thenReturn(true);

        // Act
        ArrayList<String> errors = userRepository.AddUser(goodUser);

        // Assert
        assertTrue(errors.size() == 0);
    }

    @Test
    public void UpdateUserInvalid() throws Exception {
        // Arrange
        when(phoneValidator.isPhoneNumberValid(anyString())).thenReturn(false);

        // Act
        ArrayList<String> errors = userRepository.UpdateUser(goodUser);

        // Assert
        assertTrue(errors.size() == 1);
        assertEquals("Phone number is invalid.", errors.get(0));
    }

    @Test
    public void UpdateUserValid() throws Exception {
        // Arrange
        when(phoneValidator.isPhoneNumberValid(anyString())).thenReturn(true);

        // Act
        ArrayList<String> errors = userRepository.UpdateUser(goodUser);

        // Assert
        assertTrue(errors.size() == 0);
    }

    @Test
    public void DeleteUserInvalid() throws Exception {
        // Arrange

        // Act
        ArrayList<String> errors = userRepository.DeleteUser(nullUserUUID);

        // Assert
        assertTrue(errors.size() == 1);
        assertEquals("User not found.", errors.get(0));
    }

    @Test
    public void DeleteUserValid() throws Exception {
        // Arrange

        // Act
        ArrayList<String> errors = userRepository.DeleteUser(goodUserUUID);

        // Assert
        assertTrue(errors.size() == 0);
    }
}
