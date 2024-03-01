package Application;

import Abstactions.ITransactionRepository;
import Contracts.IAccount;
import Contracts.Transaction;
import Exceptions.AccountException;
import Models.Operation;
import Models.PositiveValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DebitAccount implements IAccount {
    private final Integer yearDays = 365;
    private final PositiveValue accountId;
    private final ITransactionRepository transactionRepository;
    private Float money;
    private Float currentMoney;
    private Float percent;

    @Override
    public Boolean withdrawMoney(Float money) {
        if (money < 0)
            throw new AccountException("deposit money < 0");
        if (this.money >= money) {
            this.money -= money;
            transactionRepository.addTransaction(new Transaction(money, Operation.withdraw));
            return true;
        }
        return false;
    }

    @Override
    public void depositMoney(Float money) {
        this.money += money;
        transactionRepository.addTransaction(new Transaction(money, Operation.deposit));
    }

    @Override
    public void addCurrentMoneyInDay() {
        currentMoney += money * percent / yearDays;
    }

    @Override
    public void sumCurrentMoneyInAccount() {
        money += currentMoney;
        currentMoney = 0f;
    }

    @Override
    public void backUp() {
        var transaction = transactionRepository.getLastTransaction();
        if(transaction.isEmpty())
            return;
        if (transaction.get().operation() == Operation.deposit) {
            withdrawMoney(transaction.get().money());
        } else
            depositMoney(money);
    }
}
