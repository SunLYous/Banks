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
public class DepositAccount implements IAccount {
    private final Integer sumForFirstPercent = 50000;
    private final Integer sumForSecondPercent = 100000;
    private final ITransactionRepository transactionRepository;
    private final Integer yearDays = 365;
    private final PositiveValue accountId;
    private final Integer term;
    private Float lowPercent;
    private Float middlePercent;
    private Float highPercent;
    private Float money;
    private Integer countTerm;
    private Float currentMoney;

    @Override
    public Boolean withdrawMoney(Float money) {
        if (money < 0)
            throw new AccountException("withdraw money < 0");
        if (this.money < money || term > 0) {
            return false;
        }
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
        countTerm--;
        if (money < sumForFirstPercent) {
            currentMoney += (money * lowPercent) / yearDays;
        } else if (money > sumForFirstPercent && money < sumForFirstPercent) {
            currentMoney += (money * middlePercent) / yearDays;
        } else if (money >= sumForSecondPercent) {
            currentMoney += (money * highPercent) / yearDays;
        }
    }

    @Override
    public void sumCurrentMoneyInAccount() {
        money += currentMoney;
        currentMoney = 0f;
        countTerm = term;
    }

    @Override
    public void backUp() {
        var transaction = transactionRepository.getLastTransaction();
        if (transaction.isEmpty())
            return;
        if (transaction.get().operation() == Operation.deposit) {
            withdrawMoney(transaction.get().money());
        } else
            depositMoney(money);
    }
}
