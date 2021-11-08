package J2.geekbrains.hw5;

import java.util.Arrays;

public class Main {

    static final int size = 10_000_000;
    static final int h = size / 2;

    public static void main(String[] args) {

        firstMethod();
        secondMethod();

    }

    private static void firstMethod() {

        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
//        System.out.println(Thread.currentThread().getName() + ": " + "Массив arr: " + Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
//        System.out.println(Thread.currentThread().getName() + ": " + "Массив arr после опереции: " + Arrays.toString(arr));
        System.out.println("Время работы метода: " + Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + (System.currentTimeMillis() - startTime) / 1000f + " сек. " + "\n");
    }

    private static void secondMethod() {

        float[] array = new float[size];
        float[] array1 = new float[h];
        float[] array2 = new float[h];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.arraycopy(array, 0, array1, 0, h);
        System.arraycopy(array, h, array2, 0, h);

//        System.out.println(Thread.currentThread().getName() + ": " + "array: " + Arrays.toString(array));

        long startTime = System.currentTimeMillis();
        float[] arrayMerged = new float[size];

        new Thread(() -> {

            for (int i = 0; i < array1.length; i++) {
                array1[i] = (float) (array1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(array1, 0, arrayMerged, 0, h);
//            System.out.println(Thread.currentThread().getName() + ": " + "array1 после операции: " + Arrays.toString(array1));
        }).start();

        new Thread(() -> {

            for (int i = 0; i < array2.length; i++) {
                array2[i] =
                        (float) (array2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(array2, 0, arrayMerged, h, h);
//            System.out.println(Thread.currentThread().getName() + ": " + "array2 после операции: " + Arrays.toString(array2));

        }).start();
//        System.out.println(Thread.currentThread().getName() + ": " + "arrayMerged после операции: " + Arrays.toString(arrayMerged));
        System.out.println("Время работы метода: " + Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + (System.currentTimeMillis() - startTime) / 1000f + " сек. " + "\n");
    }
}
