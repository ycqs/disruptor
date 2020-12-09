package com.lmax.disruptor.jiaotaotest.arrayblock;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.jiaotaotest.arrayblock.MainContrast;
import com.lmax.disruptor.jiaotaotest.helloworld.LongEvent;

public class LongEventHandlerContrast implements EventHandler<LongContrastEvent>{

    private long startTime;

    public LongEventHandlerContrast(){
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void onEvent(final LongContrastEvent event, final long sequence, final boolean endOfBatch) throws Exception{
        if(event.get() == MainContrast.NUM){
            System.out.println("=="+(System.currentTimeMillis()-startTime));//12791
        }
    }
}
