package ru.job4j.bank;

import java.util.Objects;

/**
 * Модель банковского счета.
 *
 * @author Sergey Vechkitov
 * @version 1.0
 */
public class Account {
    /**
     * Реквизиты банковского счета
     */
    private String requisite;

    /**
     * Баланс банковского счета
     */
    private double balance;

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * Возвращает реквизиты банковского счета
     *
     * @return реквизиты банковского счета
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     * Возвращает баланс банковского счета
     *
     * @return баланс банковского счета
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Устанавливает баланс банковского счета равным переданному значению
     *
     * @param balance новое значение баланса банковского счета
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
