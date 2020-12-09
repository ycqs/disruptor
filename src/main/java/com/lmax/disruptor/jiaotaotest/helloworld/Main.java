package com.lmax.disruptor.jiaotaotest.helloworld;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        //创建工厂，作用是用来创建需要传递的对象
        LongEventFactory factory = new LongEventFactory();
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(
                factory,
                1024,
                DaemonThreadFactory.INSTANCE
        );
        /*Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(
                factory,
                1024,
                Executors.newSingleThreadExecutor()
        );
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(
                factory,
                1024,
                Executors.newFixedThreadPool(2)
        );*/

        //设置消费者
        disruptor.handleEventsWith(new LongEventHandler(0,1));

        /*disruptor.handleEventsWith(new LongEventHandler(0,2)
                ,new LongEventHandler2(1,2));*/
        //启动
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        for (long l = 1; true; l++)
        {
            producer.onData(l);
            Thread.sleep(1000);
        }
    }
}
