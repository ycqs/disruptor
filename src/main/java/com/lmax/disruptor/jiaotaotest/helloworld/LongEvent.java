package com.lmax.disruptor.jiaotaotest.helloworld;

public class LongEvent {
    private long value;
    public void set(long value){
        this.value = value;
    }
    public long get(){
        return value;
    }
}
