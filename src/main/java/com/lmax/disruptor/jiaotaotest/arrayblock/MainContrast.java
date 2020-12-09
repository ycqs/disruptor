package com.lmax.disruptor.jiaotaotest.arrayblock;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.TimeoutBlockingWaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.jiaotaotest.helloworld.LongEvent;
import com.lmax.disruptor.jiaotaotest.helloworld.LongEventFactory;
import com.lmax.disruptor.jiaotaotest.helloworld.LongEventProducer;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MainContrast {
    public static long NUM = 100000000;
    public static void main(String[] args) throws InterruptedException{
        Disruptor<LongContrastEvent> disruptor = new Disruptor<LongContrastEvent>(
                new EventFactory<LongContrastEvent>(){
                    @Override
                    public LongContrastEvent newInstance(){
                        return new LongContrastEvent();
                    }
                },
                1024,
                DaemonThreadFactory.INSTANCE,
                ProducerType.SINGLE,
                new BusySpinWaitStrategy()
        );
        disruptor.handleEventsWith(new LongEventHandlerContrast());
        disruptor.start();
        RingBuffer<LongContrastEvent> ringBuffer = disruptor.getRingBuffer();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable(){
            @Override
            public void run(){
                for (long l = 1; l <= MainContrast.NUM ; l++){
                    long sequence = ringBuffer.next();
                    try{
                        LongContrastEvent event = ringBuffer.get(sequence);
                        event.set(l);
                    }finally{
                        ringBuffer.publish(sequence);
                    }
                }
                latch.countDown();
            }
        }).start();
        latch.await();
        disruptor.shutdown();
    }
}
