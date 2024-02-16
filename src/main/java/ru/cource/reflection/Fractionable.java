package ru.cource.reflection;

public interface Fractionable {
    @Cached
    double doubleValue();
    @Cached
    double invertedDoubleValue();
    @Mutator
    void setNum(int num);
    @Mutator
    void setDenum(int denum);
}
