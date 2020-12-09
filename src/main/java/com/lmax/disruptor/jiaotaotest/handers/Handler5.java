package com.lmax.disruptor.jiaotaotest.handers;

import com.lmax.disruptor.EventHandler;

public class Handler5 implements EventHandler<DataEvent>{

    @Override
    public void onEvent(final DataEvent event, final long sequence, final boolean endOfBatch) throws Exception{
        System.out.println("handler5:height="+event.getHeight());
    }
}
