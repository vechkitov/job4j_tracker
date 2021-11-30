package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает простейшую модель Банка.
 *
 * @author Sergey Vechkitov
 * @version 1.0
 */
public class BankService {
    /**
     * Коллекция клиентов банка и их банковских счетов
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет нового клиента банка в коллекцию. Клиент будет добавлен только если
     * его еще нет в коллекции
     *
     * @param user клиент банка
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод добавляет счет клиенту банка. Клиент уже должен содержаться в коллекции. Поиск клиента
     * осуществляется по его паспортным данным. Счет будет добавлен, только если такого счета еще
     * нет у этого клиента.
     *
     * @param passport пасспортные данные клиента
     * @param account  банковский счет, который надо добавить клиенту
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Ищет клиента банка по его пасспортным данным.
     *
     * @param passport пасспортные данные клиента
     * @return клиент банка, если он найден, иначе null.
     */
    public User findByPassport(String passport) {
        User rsl = null;
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                rsl = user;
                break;
            }
        }
        return rsl;
    }

    /**
     * Ищет банковский счет клиента банка по пасспортным данных клиента и
     * реквизитам банковского счета.
     *
     * @param passport  пасспортные данные клиента
     * @param requisite реквизиты банковского счета
     * @return банковский счет, если найден, иначе null
     */
    public Account findByRequisite(String passport, String requisite) {
        Account rsl = null;
        User user = findByPassport(passport);
        if (user != null) {
            for (Account account : users.get(user)) {
                if (account.getRequisite().equals(requisite)) {
                    rsl = account;
                    break;
                }
            }
        }
        return rsl;
    }

    /**
     * Переводит деньги между банковскими счетами.
     *
     * @param srcPassport   пасспортные данные клиента, который отправляет деньги.
     * @param srcRequisite  реквизиты банковского счета, с которого будут списаны деньги.
     * @param destPassport  пасспортные данные клиента, который получает деньги.
     * @param destRequisite реквизиты банковского счета, куда будут зачислены деньги.
     * @param amount        сумма перевода
     * @return true, если операция прошла успешно, иначе false
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount == null || destAccount == null || srcAccount.getBalance() < amount) {
            return false;
        }
        srcAccount.setBalance(srcAccount.getBalance() - amount);
        destAccount.setBalance(destAccount.getBalance() + amount);
        return true;
    }
}
