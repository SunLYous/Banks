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
public class CreditAccount implements IAccount {
    private final PositiveValue accountId;
    private final ITransactionRepository transactionRepository;
    private Float money;
    private Float currentCommission;
    private Float commission;

    @Override
    public Boolean withdrawMoney(Float money) {
        if (money < 0)
            throw new AccountException("withdraw money < 0");
        this.money -= money;
        transactionRepository.addTransaction(new Transaction(money, Operation.withdraw));
        return true;
    }

    @Override
    public void depositMoney(Float money) {
        if (money < 0)
            throw new AccountException("deposit money < 0");
        this.money += money;
        transactionRepository.addTransaction(new Transaction(money, Operation.deposit));
    }

    @Override
    public void addCurrentMoneyInDay() {
        if (money < 0)
            currentCommission += commission;
    }

    @Override
    public void sumCurrentMoneyInAccount() {
        money -= currentCommission;
        commission = 0f;
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
