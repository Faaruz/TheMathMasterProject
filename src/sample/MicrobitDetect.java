package sample;

import java.io.File;

public class MicrobitDetect {
    public static boolean drive = false;

    public static boolean returnExampleBool () {
        return drive;
    }

    static File[] oldListRoot = File.listRoots();
    public static void main(String[] args) {
        MicrobitDetect.waitForNotifying();
    }

    public static void waitForNotifying() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (File.listRoots().length > oldListRoot.length) {
                        System.out.println("new drive detected");
                        oldListRoot = File.listRoots();
                        System.out.println("drive"+oldListRoot[oldListRoot.length-1]+" detected");
                        drive = true;
                        System.out.println(returnExampleBool());
                        break;

                    } else if (File.listRoots().length < oldListRoot.length) {
                        System.out.println(oldListRoot[oldListRoot.length-1]+" drive removed");
                        drive = false;
                        System.out.println(returnExampleBool());
                        oldListRoot = File.listRoots();
                    }
                }
            }
        });
        t.start();
    }
}
