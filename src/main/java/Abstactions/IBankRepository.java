package Abstactions;

import Contracts.IBank;
import Models.PositiveValue;

import java.util.List;
import java.util.Optional;

public interface IBankRepository {
    void addBank(IBank bank);
    Optional<IBank> getBank(PositiveValue index);
    List<IBank> getBanks();
    Integer size();
}
