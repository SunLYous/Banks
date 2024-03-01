package Application;

import Abstactions.IUserRepository;
import Contracts.*;
import Exceptions.BankException;
import Models.PositiveValue;
import Models.ValuesConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
public class Bank implements IBank, ISubject {
    private final IUserRepository userRepository;
    private final ICentralBank centralBank;
    private final List<IObserver> observers;
    private String name;
    private ValuesConfig config;
    private Integer countUSer = 1;

    public void setInterest(ValuesConfig config) {
        this.config = config;
    }

    @Override
    public Optional<User> findUser(PositiveValue accountId) {
        return userRepository.getUser(accountId);
    }

    @Override
    public void addNewUser(User user) {
         userRepository.addUser(user);
    }

    @Override
    public void cancelingLastTransaction(PositiveValue userId, PositiveValue accountId) {
        var user = findUser(accountId);
        if (user.isPresent()) {
            var account = user.get().findAccount(accountId);
            account.ifPresent(IAccount::backUp);
        }
    }

    @Override
    public Boolean transactionMoney(Float money, PositiveValue senderAccountId,
                                    PositiveValue senderUserId, PositiveValue recipientAccountId,
                                    PositiveValue recipientUserId) {
        if (money < 0)
            throw new BankException("money < 0");
        var user = findUser(senderUserId);
        if (user.isEmpty())
            return false;
        var account = user.get().findAccount(senderAccountId);
        if (account.isEmpty())
            return false;
        account.get().withdrawMoney(money);
        var userTwo = findUser(recipientUserId);
        if (userTwo.isEmpty())
            return false;
        var accountTwo = userTwo.get().findAccount(recipientAccountId);
        if (accountTwo.isEmpty())
            return false;
        accountTwo.get().depositMoney(money);
        return true;
    }

    @Override
    public void transactionMoneyOtherBank(Float money, PositiveValue senderAccountId,
                                          PositiveValue senderUserId, PositiveValue senderBankId,
                                          PositiveValue recipientAccountId, PositiveValue recipientUserId,
                                          PositiveValue recipientBankId) {
        centralBank.transactionMoneyOtherBank(
                money,
                senderAccountId,
                senderUserId,
                senderBankId,
                recipientAccountId,
                recipientUserId,
                recipientBankId
        );
    }

    @Override
    public void calculatingPercentages() {
        var users = userRepository.getUsers();
        for (var user : users) {
            var accounts = user.getAccountRepository().getAccounts();
            for (var account : accounts) {
                account.sumCurrentMoneyInAccount();
            }
        }
    }

    @Override
    public void Attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void Notify(String message) {
        for (var observer : observers) {
            observer.update(message);
        }
    }
}
