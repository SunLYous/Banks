package Infrastructure;

import Abstactions.IBankRepository;
import Contracts.IBank;
import Models.PositiveValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankRepository implements IBankRepository {
    private final List<IBank> banks = new ArrayList<>();
    @Override
    public void addBank(IBank bank) {
        banks.add(bank);
    }

    @Override
    public Optional<IBank> getBank(PositiveValue index) {
        if (index.value() < banks.size()) {
            return Optional.of(banks.get(index.value()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<IBank> getBanks() {
        return banks;
    }

    @Override
    public Integer size() {return banks.size();}
}
