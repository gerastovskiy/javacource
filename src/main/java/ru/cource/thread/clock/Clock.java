package ru.cource.thread.clock;

public class Clock implements Clockable {
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
