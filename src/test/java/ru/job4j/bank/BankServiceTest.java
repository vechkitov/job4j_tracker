package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BankServiceTest {

    @Test
    public void whenUserWithSpecifyingPassportExists() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        assertThat(bank.findByPassport("3434").get(), is(user));
    }

    @Test
    public void whenUserWithSpecifyingPassportDoesNotExist() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        assertTrue(bank.findByPassport("34").isEmpty());
    }

    @Test
    public void addUserWhenItDoesNotExist() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        assertThat(bank.findByPassport("3434").get(), is(user));
    }

    @Test
    public void addUserWhenItExists() {
        BankService bank = new BankService();
        User user1 = new User("3434", "Petr Arsentev");
        bank.addUser(user1);
        User user2 = new User("3434", "Petr Arsentev Sergeevich");
        bank.addUser(user2);
        assertThat(bank.findByPassport("3434").get(), is(user1));
    }

    @Test
    public void findByRequisiteWhenEnterCorrectPassportAndRequisite() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        Account account = new Account("5546", 150d);
        bank.addAccount(user.getPassport(), account);
        assertThat(bank.findByRequisite("3434", "5546").get(), is(account));
    }

    @Test
    public void findByRequisiteWhenEnterInvalidPassport() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150d));
        assertTrue(bank.findByRequisite("34", "5546").isEmpty());
    }

    @Test
    public void findByRequisiteWhenEnterInvalidRequisite() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150d));
        assertTrue(bank.findByRequisite("3434", "55").isEmpty());
    }

    @Test
    public void addAccountWhenItDoesNotExist() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150d));
        assertThat(
                bank.findByRequisite("3434", "5546").get().getBalance(),
                is(150d)
        );
    }

    @Test
    public void addAccountWhenItExists() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150d));
        bank.addAccount(user.getPassport(), new Account("5546", 90d));
        assertThat(
                bank.findByRequisite("3434", "5546").get().getBalance(),
                is(150d)
        );
    }

    @Test
    public void addAccountWhenEnterInvalidPassport() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount("34", new Account("5546", 150d));
        assertTrue(bank.findByRequisite("34", "5546").isEmpty());
    }

    @Test
    public void transferMoneyWhenAllIsOk() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150d));
        bank.addAccount(user.getPassport(), new Account("113", 50d));
        bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "113", 150d);
        assertThat(
                bank.findByRequisite(user.getPassport(), "113").get().getBalance(),
                is(200d)
        );
    }

    @Test
    public void transferMoneyWhenSrcAccountIsNotFound() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150d));
        bank.addAccount(user.getPassport(), new Account("113", 50d));
        assertFalse(bank.transferMoney(user.getPassport(),
                "55", user.getPassport(), "113", 150d));
        assertThat(bank.findByRequisite(
                        user.getPassport(), "5546").get().getBalance(),
                is(150d)
        );
    }

    @Test
    public void transferMoneyWhenDestAccountIsNotFound() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150d));
        bank.addAccount(user.getPassport(), new Account("113", 50d));
        assertFalse(bank.transferMoney(user.getPassport(),
                "5546", user.getPassport(), "1", 150d));
        assertThat(
                bank.findByRequisite(user.getPassport(), "5546").get().getBalance(),
                is(150d)
        );
    }

    @Test
    public void transferMoneyWhenBalanceIsNotEnough() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150d));
        bank.addAccount(user.getPassport(), new Account("113", 50d));
        assertFalse(bank.transferMoney(user.getPassport(),
                "5546", user.getPassport(), "113", 1_000d));
        assertThat(
                bank.findByRequisite(user.getPassport(), "5546").get().getBalance(),
                is(150d)
        );
    }
}
