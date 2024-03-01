package Models;

import java.util.Optional;

public record UserInformation(String name, String surname, Optional<String> addressee, Optional<PositiveValue> value) {
}
