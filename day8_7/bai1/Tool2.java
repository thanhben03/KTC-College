package bai1;

public class Tool2 implements Runnable{
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            if (i % 2 != 0) {
                try {
                    Thread.sleep(1100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Odd Number from evenThread: " + i);

            }
        }
    }
}
