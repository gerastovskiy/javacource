package ru.cource.reflection;

public class Fraction implements Fractionable{
    private int num;
    private int denum;
    public Fraction(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public void setDenum(int denum) {
        this.denum = denum;
    }
    @Override
    public double doubleValue() {
        return (double) num / denum;
    }
    @Override
    public double invertedDoubleValue() {
        return (double) denum / num;
    }
}
