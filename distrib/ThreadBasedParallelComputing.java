package org.algo.distrib;

public class ThreadBasedParallelComputing implements Runnable{
    private int[][] arr1;
    private int[][] arr2;
    private int[][] res;
    private int row;

    public ThreadBasedParallelComputing(int[][] arr1, int[][] arr2, int[][] res, int row) {
        this.arr1 = arr1;
        this.arr2 = arr2;
        this.res = res;
        this.row = row;
    }
    @Override
    public void run() {
        for(int i = 0; i < arr1[0].length; i++){
            res[row][i] = arr1[row][i] * arr2[row][i];
        }
    }
}
