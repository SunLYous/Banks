package Contracts;

import Models.PositiveValue;
import Models.ValuesConfig;
import java.util.Optional;

/**
 * Interface for central bank operations.
 */
public interface ICentralBank {

    /**
     * Creates a new bank with the given name and configuration.
     *
     * @param name   the name of the new bank
     * @param config the configuration for the new bank
     * @return the ID of the newly created bank
     */
    PositiveValue createBank(String name, ValuesConfig config);

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
     * Finds a bank by its ID.
     *
     * @param bankId the ID of the bank to find
     * @return an Optional containing the bank, if found, or empty if not found
     */
    Optional<IBank> findBank(PositiveValue bankId);
}
