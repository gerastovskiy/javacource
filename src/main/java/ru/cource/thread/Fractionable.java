package ru.cource.thread;

public interface Fractionable {
    @Cached(1500)
    double doubleValue();
    @Cached
    double invertedDoubleValue();
    void setNum(int num);
    void setDenum(int denum);
}
