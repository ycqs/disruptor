package com.lmax.disruptor.jiaotaotest.arrayblock;

public class LongContrastEvent {
    private long value;
    public void set(long value){
        this.value = value;
    }
    public long get(){
        return value;
    }
}
