package Application;

import Abstactions.IAccountRepository;
import Contracts.IAccount;
import Contracts.IAccountFactory;
import Contracts.IObserver;
import Infrastructure.TransactionRepository;
import Models.Operation;
import Models.PositiveValue;
import Models.UserInformation;
import Models.ValuesConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Stack;

@AllArgsConstructor
@Getter
public class User implements IAccountFactory, IObserver {
    private final ValuesConfig config;
    private final UserInformation information;
    private final ArrayList<String> messages;
    private final IAccountRepository accountRepository;
    private Integer countId = 0;

    @Override
    public IAccount createDepositAccount(ValuesConfig config) {
        countId++;
        var account = new DepositAccount(
                new TransactionRepository(new Stack<>()),
                new PositiveValue(countId),
                30,
                config.depositFirstInterest(),
                config.depositSecondInterest(),
                config.depositThirdInterest(),
                0f,
                30,
                0f
        );
        accountRepository.addAccount(account);
        return account;
    }

    @Override
    public IAccount createCreditAccount(ValuesConfig config) {
        countId++;
        var account = new CreditAccount(
                new PositiveValue(countId),
                new TransactionRepository(new Stack<>()),
                config.limitCredit(),
                0f,
                config.creditInterest()
        );
        accountRepository.addAccount(account);
        return account;
    }

    @Override
    public IAccount createDebitAccount(ValuesConfig config) {
        countId++;
        var account = new DebitAccount(
                new PositiveValue(countId),
                new TransactionRepository(new Stack<>()),
                0f,
                0f,
                config.debitInterest());
        accountRepository.addAccount(account);
        return account;
    }


    @Override
    public void update(String information) {
        messages.add(information);
    }

    public Optional<IAccount> findAccount(PositiveValue value) {
        return accountRepository.getAccount(value);
    }

    public Boolean makeTransaction(
            PositiveValue accountId,
            Float money,
            Operation operation) {
        var account = accountRepository.getAccount(accountId);
        if (account.isEmpty())
            return false;
        if (operation == Operation.withdraw) {
            account.get().withdrawMoney(money);
        } else
            account.get().depositMoney(money);
        return true;
    }
}
