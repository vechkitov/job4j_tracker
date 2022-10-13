package ru.job4j.early;

import java.util.Locale;
import java.util.function.IntPredicate;
import java.util.stream.Stream;

public class PasswordValidator {

    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Пароль не должен быть null");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException(String.format("Длина пароля должна быть "
                    + "в диапазоне [8, 32]. Текущая длина пароля: %d", password.length()));
        }
        if (password.chars().noneMatch(Character::isUpperCase)) {
            throw new IllegalArgumentException(String.format("Пароль должен содержать хотя бы "
                    + "один символ в верхнем регистре. Текущий пароль: %s", password));
        }
        if (password.chars().noneMatch(Character::isLowerCase)) {
            throw new IllegalArgumentException(String.format("Пароль должен содержать хотя бы "
                    + "один символ в нижнем регистре. Текущий пароль: %s", password));
        }
        if (password.chars().noneMatch(Character::isDigit)) {
            throw new IllegalArgumentException(String.format("Пароль должен содержать хотя бы "
                    + "одну цифру. Текущий пароль: %s", password));
        }
        IntPredicate predicate = Character::isLetterOrDigit;
        if (password.chars().noneMatch(predicate.negate())) {
            throw new IllegalArgumentException(String.format("Пароль должен содержать хотя бы "
                    + "один спец. символ (не цифра и не буква). Текущий пароль: %s", password));
        }
        if (Stream.of("qwerty", "12345", "password", "admin", "user")
                .anyMatch(s -> password.toLowerCase(Locale.ROOT).contains(s))) {
            throw new IllegalArgumentException(String.format("Пароль не должен подстрок без учета "
                            + "регистра: qwerty, 12345, password, admin, user. Текущий пароль: %s",
                    password));
        }
        return password;
    }
}
