package com.lmax.disruptor.jiaotaotest.handers;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.jiaotaotest.helloworld.LongEvent;

public class Handler2 implements EventHandler<DataEvent>{

    @Override
    public void onEvent(final DataEvent event, final long sequence, final boolean endOfBatch) throws Exception{
        event.setHeight(170);
        System.out.println("handler2设置完成");
        Thread.sleep(3000);
    }
}
