import Application.CentralBank;
import Application.User;
import Infrastructure.AccountRepository;
import Infrastructure.BankRepository;
import Models.IUserBuilder;
import Models.PositiveValue;
import Models.UserBuilder;
import Models.ValuesConfig;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {
    @Test
    public void shouldCreateBank() {
        var centerBank = new CentralBank(new BankRepository());
        var config = new ValuesConfig(1f, 2f,
                3f, 1.2f,
                1.3f, 300000f);
        var sberId = centerBank.createBank("Сбербанк", config);

        assertEquals(sberId.value(), 0);
    }

    @Test
    public void shouldDepositMoney() {
        var centerBank = new CentralBank(new BankRepository());
        var config = new ValuesConfig(1f, 2f,
                3f, 1.2f,
                1.3f, 300000f);
        var sberId = centerBank.createBank("Сбербанк", config);
        var sber = centerBank.findBank(sberId);
        var userInformation = new UserBuilder().addName("Nahid").addSurname("Abbasov").addAddressee("ddd").addPassportNumber(new PositiveValue(233)).Build();
        var user = new User(config, userInformation, new ArrayList<>(), new AccountRepository(), 0);
        sber.get().addNewUser(user);
        var account = user.createDebitAccount(config);
        account.depositMoney(100f);
        assertEquals(account.getMoney(), 100);
    }

    @Test
    public void shouldWithdrawMoney() {
        var centerBank = new CentralBank(new BankRepository());
        var config = new ValuesConfig(1f, 2f,
                3f, 1.2f,
                1.3f, 300000f);
        var sberId = centerBank.createBank("Сбербанк", config);
        var sber = centerBank.findBank(sberId);
        var userInformation = new UserBuilder().addName("Nahid").addSurname("Abbasov").addAddressee("ddd").addPassportNumber(new PositiveValue(233)).Build();
        var user = new User(config, userInformation, new ArrayList<>(), new AccountRepository(), 0);
        sber.get().addNewUser(user);
        var account = user.createDebitAccount(config);
        account.depositMoney(100f);
        account.withdrawMoney(50f);
        assertEquals(account.getMoney(), 50);
    }

    @Test
    public void shouldBackUp() {
        var centerBank = new CentralBank(new BankRepository());
        var config = new ValuesConfig(1f, 2f,
                3f, 1.2f,
                1.3f, 300000f);
        var sberId = centerBank.createBank("Сбербанк", config);
        var sber = centerBank.findBank(sberId);
        var userInformation = new UserBuilder().addName("Nahid").addSurname("Abbasov").addAddressee("ddd").addPassportNumber(new PositiveValue(233)).Build();
        var user = new User(config, userInformation, new ArrayList<>(), new AccountRepository(), 0);
        sber.get().addNewUser(user);
        var account = user.createDebitAccount(config);
        account.depositMoney(100f);
        account.withdrawMoney(50f);
        account.backUp();
        assertEquals(account.getMoney(), 100);
    }

    @Test
    public void shouldTransactionInsideBank() {
        var centerBank = new CentralBank(new BankRepository());
        var config = new ValuesConfig(1f, 2f,
                3f, 1.2f,
                1.3f, 300000f);
        var sberId = centerBank.createBank("Сбербанк", config);
        var sber = centerBank.findBank(sberId);
        var userInformation = new UserBuilder().addName("Nahid").addSurname("Abbasov").addAddressee("ddd").addPassportNumber(new PositiveValue(233)).Build();
        var user = new User(config, userInformation, new ArrayList<>(), new AccountRepository(), 0);
        sber.get().addNewUser(user);
        var account = user.createDebitAccount(config);
        account.depositMoney(100f);
        account.withdrawMoney(50f);
        account.backUp();
        var userInformation2 = new UserBuilder().addName("Nahid").addSurname("Abbasov").addAddressee("ddd").addPassportNumber(new PositiveValue(233)).Build();
        var user2 = new User(config, userInformation2, new ArrayList<>(), new AccountRepository(), 0);
        sber.get().addNewUser(user2);
        var account2 = user2.createDebitAccount(config);
        account2.depositMoney(100f);
        sber.get().transactionMoney(20f,
                new PositiveValue(0),
                new PositiveValue(0),
                new PositiveValue(0), new PositiveValue(1));
    assertEquals(account2.getMoney(), 120);
    }

    @Test
    public void shouldTransactionOutsideBank() {
        var centerBank = new CentralBank(new BankRepository());
        var config = new ValuesConfig(1f, 2f,
                3f, 1.2f,
                1.3f, 300000f);
        var sberId = centerBank.createBank("Сбербанк", config);
        var sber = centerBank.findBank(sberId);
        var tinkId = centerBank.createBank("tinkoff", config);
        var tink = centerBank.findBank(tinkId);
        var userInformation = new UserBuilder().addName("Nahid").addSurname("Abbasov").addAddressee("ddd").addPassportNumber(new PositiveValue(233)).Build();
        var user = new User(config, userInformation, new ArrayList<>(), new AccountRepository(), 0);
        sber.get().addNewUser(user);
        var account = user.createDebitAccount(config);
        account.depositMoney(100f);
        account.withdrawMoney(50f);
        account.backUp();
        var userInformation2 = new UserBuilder().addName("Nahid").addSurname("Abbasov").addAddressee("ddd").addPassportNumber(new PositiveValue(233)).Build();
        var user2 = new User(config, userInformation2, new ArrayList<>(), new AccountRepository(), 0);
        tink.get().addNewUser(user2);
        var account2 = user2.createDebitAccount(config);
        account2.depositMoney(100f);
        sber.get().transactionMoneyOtherBank(20f,
                new PositiveValue(0),
                new PositiveValue(0),
                new PositiveValue(0),
                new PositiveValue(0),
                new PositiveValue(0),
        new PositiveValue(1));
        assertEquals(account2.getMoney(), 120);
    }
}
