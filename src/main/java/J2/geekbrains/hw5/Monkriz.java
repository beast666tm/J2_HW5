package J2.geekbrains.hw5;

import java.util.Arrays;

public class Monkriz {
    static final int size = 10_000_000;
    static final int h = size / 2;

    public static void main(String[] args) {

        firstMethod();
        secondMethod();

    }

    private static void firstMethod() {

        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i]++;
        }
//        System.out.println(Thread.currentThread().getName() + ": " + "Массив arr: " + Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
//        System.out.println(Thread.currentThread().getName() + ": " + "Массив arr после опереции: " + Arrays.toString(arr));
        System.out.println("Время работы метода: " + Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + (System.currentTimeMillis() - startTime) / 1000f + " сек. " + "\n");
    }

    public static void secondMethod() {

        final int h = size / 2;
        float[] arr = new float[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        long startTime = System.currentTimeMillis();

        float[] a1 = new float[arr.length / 2];
        float[] a2 = new float[arr.length];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, h, h);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(a1, 0, arr, 0, h);
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            for (int i = h; i < a2.length; i++) {
                a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(a2, h, arr, h, h);
        });

        thread2.start();


//        System.out.println(Thread.currentThread().getName() + ": " + "Массив arr после опереции: " + Arrays.toString(arr));
        System.out.println("Время работы метода: " + Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + (System.currentTimeMillis() - startTime) / 1000f + " сек. " + "\n");

    }
}
