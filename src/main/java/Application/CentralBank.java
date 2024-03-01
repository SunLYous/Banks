package Application;

import Abstactions.IBankRepository;
import Contracts.IBank;
import Contracts.ICentralBank;
import Contracts.IObserver;
import Contracts.ISubject;
import Exceptions.BankException;
import Infrastructure.UserRepository;
import Models.PositiveValue;
import Models.ValuesConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class CentralBank implements ICentralBank, ISubject {
    private final IBankRepository banks;
    private final List<IObserver> observers = new ArrayList<>();

    public CentralBank(IBankRepository banks) {
        this.banks = banks;
    }

    @Override
    public PositiveValue createBank(String name, ValuesConfig config) {
        var bank = new Bank(
                new UserRepository(),
                this,
                new ArrayList<>(),
                name,
                config,
                0
        );
        banks.addBank(bank);
        return new PositiveValue(banks.size() - 1);
    }

    @Override
    public void transactionMoneyOtherBank(Float money, PositiveValue senderAccountId, PositiveValue senderUserId, PositiveValue senderBankId, PositiveValue recipientAccountId, PositiveValue recipientUserId, PositiveValue recipientBankId) {
        if (banks.getBank(senderBankId).isEmpty() || banks.getBank(recipientBankId).isEmpty()) {
            throw new BankException("bank is not found");
        }
        var senderUser = banks.getBank(senderBankId).get().findUser(senderUserId);
        if (senderUser.isEmpty())
            throw new BankException("user is not found");
        var senderAccount = senderUser.get().findAccount(senderAccountId);
        if (senderAccount.isEmpty())
            throw new BankException("Account is not found");
        senderAccount.get().withdrawMoney(money);
        var recipientUser = banks.getBank(recipientBankId).get().findUser(recipientUserId);
        if (recipientUser.isEmpty())
            throw new BankException("user is not found");
        var recipientAccount = recipientUser.get().findAccount(recipientAccountId);
        if (recipientAccount.isEmpty())
            throw new BankException("Account is not found");
        recipientAccount.get().depositMoney(money);
    }

    @Override
    public Optional<IBank> findBank(PositiveValue bankId) {
        if (bankId.value() > banks.size() - 1)
            return Optional.empty();
        return banks.getBank(bankId);
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
