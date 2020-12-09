package com.lmax.disruptor.jiaotaotest.helloworld;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class LongEventProducer {
    private RingBuffer<LongEvent> ringBuffer;
    public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }
    public void onData(long l){
        long sequence = ringBuffer.next();  // Grab the next sequence
        try{
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            // for the sequence
            event.set(l);  // Fill with data
        }finally{
            ringBuffer.publish(sequence);
        }
    }
}
