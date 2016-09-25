package info.atende.nserver;

import info.atende.nserver.model.Counter;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Giovanni Silva
 */
public class TestCounter {

    @Test
    public void increment(){
        Counter counter = new Counter();
        Assert.assertEquals(0, counter.getCurrentValue());
        counter.incrementCurrentValue();
        Assert.assertEquals(1, counter.getCurrentValue());
    }

    @Test
    public void decrement(){
        Counter counter = new Counter();
        Assert.assertEquals(0, counter.getCurrentValue());
        counter.decrementCurrentValue();
        Assert.assertEquals(-1, counter.getCurrentValue());
    }

    @Test
    public void incrementFailed(){
        Counter counter = new Counter();
        Assert.assertEquals(0, counter.getTotalFailed());
        counter.incrementFailed();
        Assert.assertEquals(1, counter.getTotalFailed());
    }

    @Test
    public void incrementSended(){
        Counter counter = new Counter();
        Assert.assertEquals(0, counter.getTotalSended());
        counter.incrementTotalSended();
        Assert.assertEquals(1, counter.getTotalSended());
    }

    @Test
    public void getTotal(){
        Counter counter = new Counter();
        counter.incrementTotalSended();
        counter.incrementCurrentValue();
        counter.incrementFailed();
        Counter.Total expected = new Counter.Total(1, 1, 1);
        Assert.assertEquals(expected, counter.getTotal());
    }
}
