package com.lmax.disruptor.jiaotaotest.handers;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.jiaotaotest.helloworld.LongEvent;

public class Handler1 implements EventHandler<DataEvent>{

    @Override
    public void onEvent(final DataEvent event, final long sequence, final boolean endOfBatch) throws Exception{

        event.setAge(10);
        System.out.println("handler1设置完成");
        Thread.sleep(4000);
    }
}
