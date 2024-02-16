package ru.cource.oop;

import lombok.ToString;
import ru.cource.oop.memento.AccountMemento;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

@ToString
public class Account {
    private String client;
    private final Map<Currency, Long> value = new HashMap<>();
    private final Deque<Command> operations = new ArrayDeque<>();

    public Account(String client) {
        setPrivateClient(client);
    }
    public String getClient() {
        return client;
    }
    public Long getValue(Currency currency) {
        if (this.value.containsKey(currency)) return this.value.get(currency).longValue();
        return null;
    }
    public HashMap<Currency, Long> getValue() {
        return new HashMap<Currency, Long>(this.value);
    }
    // для обеспечения отката без записи в архив изменений
    void setPrivateValue(Currency currency, Long value) {
        if (value == null)
            this.value.remove(currency);
        else if (value.longValue() < 0L)
            throw new IllegalArgumentException("Value must be positive!");
        else
            this.value.put(currency, value);
    }
    public void setValue(Currency currency, Long value) {
        // TODO: не является ли это нарущением принципа Single Responsibility?
        this.operations.push(new UndoValue(currency, this.getValue(currency)));
        setPrivateValue(currency, value);
    }
    // для обеспечения отката без записи в архив изменений
    void setPrivateClient(String client) {
        if (client == null || client.isEmpty())
            throw new IllegalArgumentException("Client must be not empty!");
        this.client = client;
    }
    public void setClient(String client) {
        // TODO: не является ли это нарущением принципа Single Responsibility?
        this.operations.push(new UndoClient(this.client));
        setPrivateClient(client);
    }
    public boolean isUndoListEmpty() {
        return this.operations.isEmpty();
    }
    public void undo() {
        if (this.isUndoListEmpty())
            throw new IllegalArgumentException("Undo list is empty!");
        this.operations.pop().undo(this);
    }
    public AccountMemento save() {
        return new AccountMemento(client, value);
    }
    public static Account load(AccountMemento accountMemento) {
        var account = new Account(accountMemento.getClient());
        account.value.putAll(accountMemento.getValue());

        return account;
    }
}
