package ru.job4j.early;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PasswordValidatorTest {

    @Test
    public void whenPasswordIsCorrectThenReturnPassword() {
        String password = "AAbb11--";
        assertThat(PasswordValidator.validate(password), is(password));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPasswordIsNullThenThrowException() {
        PasswordValidator.validate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPasswordLengthLessThan8ThenThrowException() {
        PasswordValidator.validate("Ab1-");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPasswordLengthMoreThan32ThenThrowException() {
        PasswordValidator.validate("AAbb11-- AAbb11-- AAbb11-- AAbb11--");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPasswordDoesNotContainUppercaseCharactersThenThrowException() {
        PasswordValidator.validate("aabb11--");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPasswordDoesNotContainLowercaseCharactersThenThrowException() {
        PasswordValidator.validate("AABB11--");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPasswordDoesNotContainAnyNumberThenThrowException() {
        PasswordValidator.validate("AAbbCc--");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPasswordContainsDigitsAndLettersOnlyThenThrowException() {
        PasswordValidator.validate("AaBb1122");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPasswordIsWeakThenThrowException() {
        PasswordValidator.validate("Aa1_qwerty");
    }
}
