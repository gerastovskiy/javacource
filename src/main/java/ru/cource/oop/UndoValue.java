package ru.cource.oop;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class UndoValue implements Command{
    private final Currency currency;
    private final Long value;
    @Override
    public void undo(Account account) {
        account.setPrivateValue(currency,value);
    }
}
