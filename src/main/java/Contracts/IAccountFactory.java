package Contracts;

import Models.ValuesConfig;

/**
 * Interface for creating bank account objects.
 */
public interface IAccountFactory {

    /**
     * Creates a deposit account based on the provided configuration.
     *
     * @param config the configuration for the deposit account
     * @return a new deposit account
     */
    IAccount createDepositAccount(ValuesConfig config);

    /**
     * Creates a credit account based on the provided configuration.
     *
     * @param config the configuration for the credit account
     * @return a new credit account
     */
    IAccount createCreditAccount(ValuesConfig config);

    /**
     * Creates a debit account based on the provided configuration.
     *
     * @param config the configuration for the debit account
     * @return a new debit account
     */
    IAccount createDebitAccount(ValuesConfig config);
}
