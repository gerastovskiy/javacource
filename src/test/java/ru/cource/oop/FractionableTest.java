package ru.cource.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cource.reflection.Fractionable;
import ru.cource.reflection.Utils;

public class FractionableTest {
    private FractionTest fraction;
    private Fractionable fractionCached;
    public class FractionTest implements Fractionable {
        private int num;
        private int denum;
        private int callCount = 0;
        public FractionTest(int num, int denum) {
            this.num = num;
            this.denum = denum;
        }
        public void setNum(int num) {
            this.num = num;
        }
        public void setDenum(int denum) {
            this.denum = denum;
        }
        public int getCallCount() {
            return callCount;
        }
        @Override
        public double doubleValue() {
            callCount++;
            return (double) num / denum;
        }
        @Override
        public double invertedDoubleValue() {
            callCount++;
            return (double) denum / num;
        }
    }
    @BeforeEach
    public void init(){
        fraction = new FractionTest(2, 3);
        fractionCached = Utils.cache(fraction);
    }
    @Test
    public void checkUncachedCallCount(){
        var fractionUncached = fraction;
        fractionUncached.doubleValue();
        fractionUncached.doubleValue();
        Assertions.assertEquals(2,fraction.getCallCount());
    }
    @Test
    public void checkCachedCallSingleMethodWithoutMutatorCallCount(){
        fractionCached.doubleValue();
        fractionCached.doubleValue();
        Assertions.assertEquals(1,fraction.getCallCount());
    }
    @Test
    public void checkCachedCallSingleMethodWithMutatorCallCount(){
        fractionCached.doubleValue();
        fractionCached.doubleValue();
        fractionCached.setNum(5);
        fractionCached.doubleValue();
        fractionCached.doubleValue();
        Assertions.assertEquals(2,fraction.getCallCount());
    }
    @Test
    public void checkCachedCallDoubleMethodsWithoutMutatorCallCount(){
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(2,fraction.getCallCount());
    }
    @Test
    public void checkCachedCallDoubleMethodsWithMutatorCallCount(){
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        fractionCached.setNum(5);
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(4,fraction.getCallCount());
    }
    @Test
    public void checkCachedCallDoubleMethodsWithoutMutatorCallValues(){
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(fraction.doubleValue(),fractionCached.doubleValue());
        Assertions.assertEquals(fraction.invertedDoubleValue(),fractionCached.invertedDoubleValue());
    }
    @Test
    public void checkCachedCallDoubleMethodsWithMutatorCallValues(){
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        fractionCached.setNum(5);
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(fraction.doubleValue(),fractionCached.doubleValue());
        Assertions.assertEquals(fraction.invertedDoubleValue(),fractionCached.invertedDoubleValue());
    }
}
