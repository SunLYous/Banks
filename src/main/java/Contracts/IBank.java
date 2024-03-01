package Contracts;

import Application.User;
import Models.PositiveValue;
import Models.ValuesConfig;
import java.util.Optional;

/**
 * Interface for bank operations.
 */
public interface IBank {

    /**
     * Sets the interest rate for the bank.
     *
     * @param config the configuration for setting the interest rate
     */
    void setInterest(ValuesConfig config);

    /**
     * Finds a user by their ID.
     *
     * @param userId the ID of the user to find
     * @return an Optional containing the user, if found, or empty if not found
     */
    Optional<User> findUser(PositiveValue userId);

    /**
     * Adds a new user to the bank.
     *
     * @param user the user to add
     */
    void addNewUser(User user);

    /**
     * Cancels the last transaction for a specific user and account.
     *
     * @param userId    the ID of the user
     * @param accountId the ID of the account
     */
    void cancelingLastTransaction(PositiveValue userId, PositiveValue accountId);

    /**
     * Performs a transaction between accounts within the same bank.
     *
     * @param money             the amount of money to transfer
     * @param senderAccountId   the ID of the sender account
     * @param senderUserId      the ID of the sender user
     * @param recipientAccountId the ID of the recipient account
     * @param recipientUserId    the ID of the recipient user
     * @return true if the transaction was successful, false otherwise
     */
    Boolean transactionMoney(Float money, PositiveValue senderAccountId, PositiveValue senderUserId,
                             PositiveValue recipientAccountId, PositiveValue recipientUserId);

    /**
     * Performs a transaction between accounts in different banks.
     *
     * @param money               the amount of money to transfer
     * @param senderAccountId     the ID of the sender account
     * @param senderUserId        the ID of the sender user
     * @param senderBankId        the ID of the sender bank
     * @param recipientAccountId  the ID of the recipient account
     * @param recipientUserId     the ID of the recipient user
     * @param recipientBankId     the ID of the recipient bank
     */
    void transactionMoneyOtherBank(Float money, PositiveValue senderAccountId, PositiveValue senderUserId,
                                   PositiveValue senderBankId, PositiveValue recipientAccountId,
                                   PositiveValue recipientUserId, PositiveValue recipientBankId);

    /**
     * Calculates interest and updates accounts accordingly.
     */
    void calculatingPercentages();
}
