package ru.cource.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import ru.cource.oop.memento.AccountCaretaker;

class AccountTest {
    private static final String CLIENT = "Alexander Alexandrovich";
    private static final Currency CURRENCY_RUB = Currency.RUB;
    private static final Currency CURRENCY_USD = Currency.USD;

    @Test
    public void checkCorrectAccountCreated(){
        try {
            var account = new Account(CLIENT);
        }
        catch (Exception ex) {
            throw new AssertionFailedError("Correct account creation error!");
        }
    }
    @Test
    public void checkIncorrectAccountCreated(){
        try {
            var account = new Account(null);
        }
        catch (IllegalArgumentException ex) {return;}
        throw new AssertionFailedError("Correct account creation error!");
    }
    @Test
    public void checkCorrectValuesSetAndGet(){
        var value = 0L;
        var account = new Account(CLIENT);

        account.setValue(CURRENCY_RUB, value);
        Assertions.assertEquals(value,account.getValue(CURRENCY_RUB));

        value = 100L;
        account.setValue(CURRENCY_USD, value);
        Assertions.assertEquals(value,account.getValue(CURRENCY_USD));

        account.setValue(CURRENCY_USD, null);
        Assertions.assertNull(account.getValue(CURRENCY_USD));
    }
    @Test
    public void checkincorrectValueSet(){
        var value = -1L;
        var account = new Account(CLIENT);

        try {
            account.setValue(CURRENCY_RUB, value);
        }
        catch (Exception ex) { return; }

        throw new AssertionFailedError("Incorrect value set error!");
    }
    @Test
    public void checkCorrectClientSetAndGet(){
        var account = new Account("Test Testovichus");
        account.setClient(CLIENT);

        Assertions.assertEquals(CLIENT,account.getClient());
    }
    @Test
    public void checkIncorrectClientSetAndGet(){
        var account = new Account(CLIENT);

        try {
            account.setClient(null);
        }
        catch (Exception ex) { return; }

        throw new AssertionFailedError("Incorrect client set error!");
    }
    @Test
    public void checkEmptyUndoError(){
        var account = new Account(CLIENT);

        try {
            account.undo();
        }
        catch (Exception ex) { return; }

        throw new AssertionFailedError("Incorrect empty undo error!");
    }
    @Test
    public void checkCorrectUndoClient(){
        var account = new Account(CLIENT);
        account.setClient("Test Testovichus");
        account.undo();

        Assertions.assertEquals(account.getClient(),CLIENT);
    }
    @Test
    public void checkCorrectUndoValue(){
        var value = 100L;
        var account = new Account(CLIENT);

        account.setValue(CURRENCY_RUB, value);
        account.setValue(CURRENCY_RUB, 1000L);
        account.setValue(CURRENCY_USD, 10L);
        account.undo();
        account.undo();

        Assertions.assertEquals(value,account.getValue(CURRENCY_RUB));
    }
    @Test
    public void checkMementoSaveUndo(){
        var value1 = 100L;
        var value2 = 10L;
        var caretaker = new AccountCaretaker();
        var account = new Account(CLIENT);

        caretaker.save(account.save());
        account.setValue(CURRENCY_RUB, value1);
        account.setValue(CURRENCY_USD, value2);
        caretaker.save(account.save());

        account.setValue(CURRENCY_RUB,0L);
        account.setValue(CURRENCY_RUB,1000L);
        account.setValue(CURRENCY_USD,null);

        // первый откат
        account = Account.load(caretaker.load());
        Assertions.assertEquals(value1,account.getValue(CURRENCY_RUB));
        Assertions.assertEquals(value2,account.getValue(CURRENCY_USD));

        // второй откат
        account = Account.load(caretaker.load());
        Assertions.assertNull(account.getValue(CURRENCY_RUB));
        Assertions.assertNull(account.getValue(CURRENCY_USD));
    }
}