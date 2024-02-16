package ru.cource.oop;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class UndoClient implements Command{
    private final String client;
    @Override
    public void undo(Account account){ account.setPrivateClient(client); }
}
