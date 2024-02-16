package ru.cource.oop.memento;

import java.util.ArrayDeque;
import java.util.Deque;

public class AccountCaretaker {
    private final Deque<AccountMemento> mementoStack = new ArrayDeque<>();
    public void save(AccountMemento accountMemento){
        mementoStack.push(accountMemento);
    }
    public AccountMemento load() {
        if (isNotEmpty())
            return mementoStack.pop();
        return null;
    }
    public boolean isNotEmpty(){
        return !mementoStack.isEmpty();
    }
}
