package Models;

import Application.User;

import java.util.Optional;

public class UserBuilder implements IUserBuilder {
    String name;
    String surname;
    Optional<String> addressee;
    Optional<PositiveValue> password;

    @Override
    public IUserBuilder addName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public IUserBuilder addSurname(String surname) {
        this.surname = surname;
        return this;
    }

    @Override
    public IUserBuilder addAddressee(String addressee) {
        this.addressee = addressee.describeConstable();
        return this;
    }

    @Override
    public IUserBuilder addPassportNumber(PositiveValue integer) {
        this.password = Optional.ofNullable(integer);
        return this;
    }

    @Override
    public UserInformation Build() {
        return new UserInformation(name, surname, addressee, password);
    }
}
