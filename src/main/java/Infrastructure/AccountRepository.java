package Infrastructure;

import Abstactions.IAccountRepository;
import Contracts.IAccount;
import Models.PositiveValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository implements IAccountRepository {
    private List<IAccount> accounts = new ArrayList<>();

    @Override
    public void addAccount(IAccount account) {
        accounts.add(account);
    }

    @Override
    public Optional<IAccount> getAccount(PositiveValue index) {
        if (index.value() < accounts.size())
            return Optional.of(accounts.get(index.value()));
        return Optional.empty();
    }

    @Override
    public List<IAccount> getAccounts() {
        return accounts;
    }
}
