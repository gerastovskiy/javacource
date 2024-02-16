package ru.cource.oop.memento;

import lombok.Getter;
import ru.cource.oop.Currency;
import java.util.HashMap;
import java.util.Map;

@Getter
public class AccountMemento {
    private final String client;
    private final Map<Currency, Long> value;

    public AccountMemento(String client, Map<Currency, Long> value) {
        this.client = client;
        this.value = new HashMap<>();
        this.value.putAll(value);
    }
}
