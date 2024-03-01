package Models;

public record PositiveValue(Integer value) {
    public PositiveValue {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be non-negative");
        }
    }
}

