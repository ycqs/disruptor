package com.lmax.disruptor.jiaotaotest.handers;

import com.lmax.disruptor.EventHandler;

public class Handler4 implements EventHandler<DataEvent>{

    @Override
    public void onEvent(final DataEvent event, final long sequence, final boolean endOfBatch) throws Exception{

        System.out.println("handler4:age="+event.getAge());
    }
}
