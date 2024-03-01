package Abstactions;

import Contracts.IAccount;
import Models.PositiveValue;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository {
    void addAccount(IAccount account);
    Optional<IAccount> getAccount(PositiveValue index);
    List<IAccount> getAccounts();
}
