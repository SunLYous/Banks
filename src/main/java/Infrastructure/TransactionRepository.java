package Infrastructure;

import Abstactions.ITransactionRepository;
import Contracts.Transaction;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.Stack;

@AllArgsConstructor
public class TransactionRepository implements ITransactionRepository {
    private final Stack<Transaction> transactions;

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public Optional<Transaction> getLastTransaction() {
        if (transactions.empty())
            return Optional.empty();
        return Optional.ofNullable(transactions.pop());
    }
}
