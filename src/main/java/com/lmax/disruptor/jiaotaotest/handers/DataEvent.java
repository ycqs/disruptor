package com.lmax.disruptor.jiaotaotest.handers;

public class DataEvent {

    private String name;
    private int height;
    private int age;


    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(final int age)
    {
        this.age = age;
    }

    public void setHeight(int height){
        this.height = height;
    }
    public long getHeight(){
        return height;
    }
}
