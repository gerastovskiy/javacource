package ru.cource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.cource.spring.AppConfig;
import ru.cource.spring.Worker;
import ru.cource.spring.read.FolderReader;

import java.sql.*;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(Main.class,args);
        Worker worker = context.getBean("worker", Worker.class);
        worker.make();

//  thread
//        var fraction = new Fraction(2, 3);
//        Fractionable fractionCached = CacheProxyUtils.cache(fraction);
//
//        System.out.println("Cache init 2/3");
//        System.out.println("1: " + fractionCached.doubleValue());
//        System.out.println("2: " + fractionCached.doubleValue());
//
//        fractionCached.setNum(5);
//        System.out.println("setNum 5");
//        System.out.println("3: " + fractionCached.doubleValue());
//        System.out.println("4: " + fractionCached.doubleValue());
//
//        fractionCached.setNum(2);
//        System.out.println("setNum 2");
//        System.out.println("5: " + fractionCached.doubleValue());
//        System.out.println("6: " + fractionCached.doubleValue());
//
//        System.out.println("sleep");
//        Thread.sleep(1500L);
//        System.out.println("7: " + fractionCached.doubleValue());
//        System.out.println("8: " + fractionCached.doubleValue());

//  reflection
//        var fraction = new Fraction(2, 3);
//        Fractionable fractionCached = Utils.cache(fraction);
//        System.out.println("Cache init");
//        System.out.println("11: "+fractionCached.doubleValue());
//        System.out.println("12: "+fractionCached.doubleValue());
//        System.out.println("13: "+fractionCached.doubleValue());

//        fractionCached.setNum(5);

//        System.out.println("New values set");
//        System.out.println("21: "+fractionCached.doubleValue());
//        System.out.println("21: "+fractionCached.invertedDoubleValue());
//        System.out.println("22: "+fractionCached.doubleValue());
//        System.out.println("21: "+fractionCached.invertedDoubleValue());
//        System.out.println("23: "+fractionCached.doubleValue());
//        System.out.println("21: "+fractionCached.invertedDoubleValue());

//  oop
//        var caretaker = new AccountCaretaker();
//        var account = new Account("Alexander Alexandrovich");
//
//        caretaker.save(account.save());
//        account.setValue(Currency.RUB, 1000L);
//        account.setValue(Currency.USD, 50L);
//        caretaker.save(account.save());

//        account.setClient("Vasily Alibabaevich");
//        account.setValue(Currency.RUB, 0L);
//        System.out.println("---undo test---");
//        System.out.println(account);
//        account.undo();
//        System.out.println(account);
//        account.undo();
//        System.out.println(account);
//        account.undo();
//        System.out.println(account);
//        account.undo();
//        System.out.println(account);

//        System.out.println("---load test---");
//        account = Account.load(caretaker.load());
//        System.out.println(account);
    }
}