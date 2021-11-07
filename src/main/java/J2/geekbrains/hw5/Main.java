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

    private static void secondMethod() {

        float[] array = new float[size];
        float[] array1 = new float[h];
        float[] array2 = new float[h];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1.0f;
        }
//        System.out.println(Thread.currentThread().getName() + ": " + "array: " + Arrays.toString(array));

        long startTime = System.currentTimeMillis();

        new Thread(() -> {
            float[] array11 = new float[h];
            System.arraycopy(array, 0, array1, 0, array1.length);
//            float[] array11 = Arrays.copyOfRange(array, 0, h);
//            System.out.println(Thread.currentThread().getName() + ": " + "array1: " + Arrays.toString(array11));
            for (int i = 0; i < array11.length; i++) {
                array11[i] = (float) (array11[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
//            System.out.println(Thread.currentThread().getName() + ": " + "array1 после операции: " + Arrays.toString(array11));
        }).start();

        new Thread(() -> {
            float[] array21 = new float[h];
            System.arraycopy(array, array1.length, array2, 0, array2.length);
//            float[] array21 = Arrays.copyOfRange(array, h, h);
//            System.out.println(Thread.currentThread().getName() + ": " + "array2: " + Arrays.toString(array21));
            for (int i = 0; i < array21.length; i++) {
                array21[i] = (float) (array21[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
//            System.out.println(Thread.currentThread().getName() + ": " + "array2 после операции: " + Arrays.toString(array21));
        }).start();

        float[] arrayMerged = new float[size];
        System.arraycopy(array1, 0, arrayMerged, 0, h);
        System.arraycopy(array2, 0, arrayMerged, h, h );

        System.out.println("Время работы метода: " + Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + (System.currentTimeMillis() - startTime) / 1000f + " сек. " + "\n");
        System.out.println(Thread.currentThread().getName() + ": " + "arrayMerged после операции: " + Arrays.toString(arrayMerged));
    }


}
