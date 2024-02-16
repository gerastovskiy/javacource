package ru.cource.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cource.thread.CacheProxyUtils;
import ru.cource.thread.Fractionable;
import ru.cource.thread.clock.Clockable;

public class ThreadTest {
    private FractionTest fraction;
    private Fractionable fractionCached;
    private int callCount = 0;
    private Clock clock;

    private class FractionTest implements Fractionable {
        private int num;
        private int denum;

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
            synchronized (this) {
                callCount++;
            }
            return (double) num / denum;
        }

        @Override
        public double invertedDoubleValue() {
            synchronized (this) {
                callCount++;
            }
            return (double) denum / num;
        }
    }

    private static class Clock implements Clockable {
        long currentTime;

        public Clock() {
            this.currentTime = System.currentTimeMillis();
        }

        @Override
        public long currentTimeMillis() {
            return this.currentTime;
        }
    }

    @BeforeEach
    public void init() {
        fraction = new FractionTest(2, 3);
        clock = new Clock();
        fractionCached = CacheProxyUtils.cache(fraction, clock);
    }

    @Test
    public void checkUncachedCallCount() {
        var fractionUncached = fraction;
        fractionUncached.doubleValue();
        fractionUncached.doubleValue();
        Assertions.assertEquals(2, fraction.getCallCount());
    }

    @Test
    public void checkCachedCallSingleMethodWithoutMutatorCallCount() {
        fractionCached.doubleValue();
        fractionCached.doubleValue();
        Assertions.assertEquals(1, fraction.getCallCount());
    }

    @Test
    public void checkCachedCallSingleMethodWithoutMutatorCallCountWithSleep() {
        fractionCached.doubleValue();

       clock.currentTime += 1500L;

        fractionCached.doubleValue();
        Assertions.assertEquals(2, fraction.getCallCount());
    }

    @Test
    public void checkCachedCallSingleMethodWithMutatorCallCount() {
        fractionCached.doubleValue();
        fractionCached.doubleValue();

        fractionCached.setNum(5);

        fractionCached.doubleValue();
        fractionCached.doubleValue();
        Assertions.assertEquals(2, fraction.getCallCount());
    }

    @Test
    public void checkCachedCallDoubleMethodsWithoutMutatorCallCount() {
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();

        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(2, fraction.getCallCount());
    }

    @Test
    public void checkCachedCallDoubleMethodsWithoutMutatorCallCountWithSleep() {
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();

        clock.currentTime += 1500L;

        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(4, fraction.getCallCount());
    }

    @Test
    public void checkCachedCallDoubleMethodsWithMutatorCallCount() {
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();

        fractionCached.setNum(5);

        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(4, fraction.getCallCount());
    }

    @Test
    public void checkCachedCallDoubleMethodsWithoutMutatorCallValues() {
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(fraction.doubleValue(), fractionCached.doubleValue());
        Assertions.assertEquals(fraction.invertedDoubleValue(), fractionCached.invertedDoubleValue());
    }

    @Test
    public void checkCachedCallDoubleMethodsWithMutatorCallValues() {
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();

        fractionCached.setNum(5);

        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(fraction.doubleValue(), fractionCached.doubleValue());
        Assertions.assertEquals(fraction.invertedDoubleValue(), fractionCached.invertedDoubleValue());
    }
}