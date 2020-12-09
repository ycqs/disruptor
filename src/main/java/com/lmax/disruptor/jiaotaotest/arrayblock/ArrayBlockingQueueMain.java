package com.lmax.disruptor.jiaotaotest.arrayblock;

import com.lmax.disruptor.jiaotaotest.helloworld.LongEvent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

public class ArrayBlockingQueueMain{
    private static ArrayBlockingQueue<LongEvent>
            queue = new ArrayBlockingQueue(1024);
    public static void main(String[] args) throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(1);
        long startTime = System.currentTimeMillis();
        new Thread(new Runnable(){
            @Override
            public void run(){
                for(long i = 1; i <= MainContrast.NUM; i++){
                    try{
                        LongEvent longEvent = new LongEvent();
                        longEvent.set(i);
                        queue.put(longEvent);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run(){
                for(long i = 1; i <= MainContrast.NUM; i++){
                    try{
                        queue.take();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

                System.out.println("=="+(System.currentTimeMillis()-startTime));//11144
                latch.countDown();
            }
        }).start();
        latch.await();
    }
}
