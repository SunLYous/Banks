package Abstactions;

import Contracts.Transaction;

import java.util.Optional;

public interface ITransactionRepository {
    void addTransaction(Transaction transaction);
    Optional<Transaction> getLastTransaction();
}
