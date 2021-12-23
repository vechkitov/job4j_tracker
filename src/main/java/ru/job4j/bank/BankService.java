package ru.job4j.bank;

import java.util.*;

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
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            List<Account> accounts = users.get(user.get());
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
    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * Ищет банковский счет клиента банка по пасспортным данных клиента и
     * реквизитам банковского счета.
     *
     * @param passport  пасспортные данные клиента
     * @param requisite реквизиты банковского счета
     * @return банковский счет, если найден, иначе null
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            return users.get(user.get()).stream()
                    .filter(account -> account.getRequisite().equals(requisite))
                    .findFirst();
        }
        return Optional.empty();
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
        Optional<Account> srcAccount = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount.isEmpty()
                || destAccount.isEmpty()
                || srcAccount.get().getBalance() < amount) {
            return false;
        }
        srcAccount.get().setBalance(
                srcAccount.get().getBalance() - amount
        );
        destAccount.get().setBalance(
                destAccount.get().getBalance() + amount
        );
        return true;
    }
}
