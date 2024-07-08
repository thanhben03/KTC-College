package bai1;

public class Application {
    public static void main(String[] args) {
        Thread t1 = new Tool();
        Runnable run = new Tool2();

        Thread t2 = new Thread(run);

        t1.start();
        t2.start();
    }
}
