package com.lmax.disruptor.jiaotaotest.helloworld;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent>{
    private final long ordinal;
    private final long numberOfConsumers;
    public LongEventHandler(final long ordinal, final long numberOfConsumers){
        this.ordinal = ordinal;
        this.numberOfConsumers = numberOfConsumers;
    }
    @Override
    public void onEvent(final LongEvent event, final long sequence, final boolean endOfBatch) throws Exception{
        if ((sequence % numberOfConsumers) == ordinal){
            System.out.println(Thread.currentThread().getName()+"=2="+event.get());
        }
    }
}
