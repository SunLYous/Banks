package Contracts;

import Models.PositiveValue;

/**
 * Interface for bank accounts service.
 */
public interface IAccount {

    /**
     * Withdraws money from the account.
     *
     * @param money the amount of money to withdraw
     * @return true if the withdrawal was successful, false otherwise
     */
    Boolean withdrawMoney(Float money);

    /**
     * Deposits money into the account.
     *
     * @param money the amount of money to deposit
     */
    void depositMoney(Float money);

    /**
     * Adds the current money in the account for the day.
     */
    void addCurrentMoneyInDay();

    /**
     * Calculates the sum of the current money in the account.
     */
    void sumCurrentMoneyInAccount();

    /**
     * Retrieves the account ID.
     *
     * @return the account ID
     */
    PositiveValue getAccountId();

    /**
     * Retrieves the amount of money in the account.
     *
     * @return the amount of money in the account
     */
    Float getMoney();

    /**
     * Performs a backup operation for the account data.
     */
    void backUp();
}
