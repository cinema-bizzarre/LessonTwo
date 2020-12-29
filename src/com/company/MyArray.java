package com.company;
import java.util.Arrays;

public class MyArray {

    static final int size = 20000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        MyArray s = new MyArray();
        s.method1();
        s.method2();


    }


    public float[] calculate(float[] arr) {

        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + arr[i] / 5) * Math.cos(0.2f + arr[i] / 5) *
                    Math.cos(0.4f + arr[i] / 2));
        return arr;
    }

    public void method1() {
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) ;
        long a = System.currentTimeMillis();
        calculate(arr);
        System.out.println("Первый метод отработал за:" + (System.currentTimeMillis() - a));

    }


    public void method2() {

        float[] arr = new float[size];
        float[] arr1 = new float[size / 2];
        float[] arr2 = new float[size / 2];
        for (int i = 0; i < arr.length; i++) ;
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);


        new Thread() {
            public void run() {
                float[] a1 = calculate(arr1);
                System.arraycopy(a1, 0, arr1, 0, a1.length);
            }
        }.start();

        new Thread() {
            public void run() {
                float[] a2 = calculate(arr2);
                System.arraycopy(a2, 0, arr2, 0, a2.length);
            }
        }.start();
        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, 0, h);
        System.out.println("Второй метод отработал за:" + (System.currentTimeMillis() - a));
    }
}

    
