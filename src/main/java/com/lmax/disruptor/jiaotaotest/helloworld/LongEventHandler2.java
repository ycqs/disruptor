package com.lmax.disruptor.jiaotaotest.helloworld;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler2 implements EventHandler<LongEvent>
{
    private final long ordinal;
    private final long numberOfConsumers;

    public LongEventHandler2(final long ordinal, final long numberOfConsumers)
    {
        this.ordinal = ordinal;
        this.numberOfConsumers = numberOfConsumers;
    }

    @Override
    public void onEvent(final LongEvent event, final long sequence, final boolean endOfBatch) throws Exception
    {
        if ((sequence % numberOfConsumers) == ordinal)
        {
            Thread.sleep(1100);
            // Process the event
            System.out.println(Thread.currentThread().getName()+"=2="+event.get());
        }
    }
}
