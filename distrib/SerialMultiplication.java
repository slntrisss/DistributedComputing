package org.algo.distrib;

public class SerialMultiplication {
    private int[][] arr1;
    private int[][] arr2;
    private int[][] res;

    public SerialMultiplication(int[][] arr1, int[][] arr2, int[][] res) {
        this.arr1 = arr1;
        this.arr2 = arr2;
        this.res = res;
    }

    public void compute(){
        int size = arr1.length;
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                res[i][j] = arr1[i][j] * arr2[i][j];
            }
        }
    }
}
