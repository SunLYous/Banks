package Infrastructure;

import Abstactions.IUserRepository;
import Application.User;
import Models.PositiveValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements IUserRepository {
    private final List<User> users = new ArrayList<>();
    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public Optional<User> getUser(PositiveValue index) {
        if (index.value() < users.size()) {
            return Optional.of(users.get(index.value()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
