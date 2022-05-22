package org.algo.distrib;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixMultiplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter matrix size NxN: ");
        int size = input.nextInt();
        System.out.print("Enter number of threads: " );
        int numberOfThreads = input.nextInt();
        System.out.println("Calculating...");
        int[][] arr1 = generateMatrices(size);
        int[][] arr2 = generateMatrices(size);
        int[][] res = new int[size][size];


        long start = System.nanoTime();
        SerialMultiplication serial = new SerialMultiplication(arr1, arr2, res);
        serial.compute();
        long end = System.nanoTime();
        System.out.println("Serial: " + (double)(end-start)/1_000_000 + " ms");

        start = System.nanoTime();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        for(int i = 0; i < arr1.length; i++){
            executorService.execute(new ThreadBasedParallelComputing(arr1, arr2, res, i));
        }
        executorService.shutdown();
        end = System.nanoTime();
        System.out.println("Parallel: " + (double)(end-start)/1_000_000 + " ms");


//        RecursiveAction task = new ForkJoinParallelMatrixMultiplication(arr1, arr2, size);
//        ForkJoinPool pool = new ForkJoinPool();
//        pool.invoke(task);

    }
    private static int[][] generateMatrices(int size){
        int[][] arr = new int[size][size];
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                arr[i][j] = (int)(Math.random()*100);
            }
        }
        return arr;
    }
}
