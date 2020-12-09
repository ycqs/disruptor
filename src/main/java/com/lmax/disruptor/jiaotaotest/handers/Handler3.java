package com.lmax.disruptor.jiaotaotest.handers;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.jiaotaotest.helloworld.LongEvent;

public class Handler3 implements EventHandler<DataEvent>{

    @Override
    public void onEvent(final DataEvent event, final long sequence, final boolean endOfBatch) throws Exception{
        System.out.println("name="+event.getName()+",age="+event.getAge()+",height="+event.getHeight());
    }
}
