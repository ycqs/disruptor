package com.lmax.disruptor.jiaotaotest.handers;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.jiaotaotest.arrayblock.LongContrastEvent;
import com.lmax.disruptor.jiaotaotest.helloworld.LongEvent;
import com.lmax.disruptor.jiaotaotest.helloworld.LongEventFactory;
import com.lmax.disruptor.jiaotaotest.helloworld.LongEventHandler;
import com.lmax.disruptor.jiaotaotest.helloworld.LongEventHandler2;
import com.lmax.disruptor.jiaotaotest.helloworld.LongEventProducer;
import com.lmax.disruptor.util.DaemonThreadFactory;

public class One2one {

    public static void main(String[] args) throws InterruptedException{
        //创建工厂，作用是用来创建需要传递的对象
        Disruptor<DataEvent> disruptor = new Disruptor<DataEvent>(
                new EventFactory<DataEvent>(){
                    @Override
                    public DataEvent newInstance(){
                        return new DataEvent();
                    }
                },
                1024,
                DaemonThreadFactory.INSTANCE
        );

        //串行
        /*disruptor.handleEventsWith(new Handler1())
                .handleEventsWith(new Handler2())
                .handleEventsWith(new Handler3());*/
        //并行
        //disruptor.handleEventsWith(new Handler1(),new Handler2(),new Handler3());

        //h1 h2并行  h3在执行
        /*disruptor.handleEventsWith(new Handler1(),new Handler2())
                .handleEventsWith(new Handler3());*/

        //h1 h2 并行，h4等h1执行完，h5等h2执行完，h3等h4，h5执行完
        Handler1 h1 = new Handler1();
        Handler2 h2 = new Handler2();
        Handler3 h3 = new Handler3();
        Handler4 h4 = new Handler4();
        Handler5 h5 = new Handler5();
        disruptor.handleEventsWith(h1,h2);
        disruptor.after(h1).handleEventsWith(h4);
        disruptor.after(h2).handleEventsWith(h5);
        disruptor.after(h4,h5).handleEventsWith(h3);

        //启动
        disruptor.start();

        RingBuffer<DataEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();
        try{
            DataEvent event = ringBuffer.get(sequence);
            event.setName("zhangsan");
        }finally{
            ringBuffer.publish(sequence);
        }
        Thread.sleep(8*1000);
        disruptor.shutdown();
    }
}
