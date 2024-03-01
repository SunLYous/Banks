package Models;

import Application.User;

public interface IUserBuilder {
    IUserBuilder addName(String name);

    IUserBuilder addSurname(String surname);

    IUserBuilder addAddressee(String addressee);

    IUserBuilder addPassportNumber(PositiveValue integer);

    UserInformation Build();
}
