package ru.job4j.bank;

import java.util.Objects;

/**
 * Модель клиента банка.
 *
 * @author Sergey Vechkitov
 * @version 1.0
 */
public class User {
    /**
     * Данные пасспорта клиента банка
     */
    private String passport;
    /**
     * Имя клиента банка
     */
    private String username;

    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * Возвращает данные пасспорта клиента банка
     *
     * @return данные пасспорта клиента банка
     */
    public String getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
