package Abstactions;

import Application.User;
import Models.PositiveValue;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    void addUser(User user);
    Optional<User> getUser(PositiveValue index);
    List<User> getUsers();
}
