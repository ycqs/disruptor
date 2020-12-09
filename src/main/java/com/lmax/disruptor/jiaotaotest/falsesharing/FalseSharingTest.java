package com.lmax.disruptor.jiaotaotest.falsesharing;

public class FalseSharingTest implements Runnable {
    private static FalseSharingPadding padding = new FalseSharingPadding();
    private int index;

    public FalseSharingTest(int index){
        this.index = index;
    }
    @Override
    public void run() {
        Long i = 500000000L;
        while (0 != --i) {
            if(index == 1){
                padding.b = i;
            }else {
                padding.a = i;
            }
        }
    }
    private static void startThread() throws InterruptedException {
        Thread t1 = new Thread(new FalseSharingTest(1));
        Thread t2 = new Thread(new FalseSharingTest(2));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
    public static void main(String[] args) throws InterruptedException {
        final long start = System.currentTimeMillis();
        startThread();
        System.out.println("耗时 = " + (System.currentTimeMillis() - start));
    }
    public static final class FalseSharingPadding {
        public volatile long a = 0L;
        public long p1, p2, p3, p4, p5, p6, p7; // 用来填充缓存行
        public volatile long b = 1;
    }
}
