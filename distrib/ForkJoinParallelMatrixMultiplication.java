package org.algo.distrib;
import java.util.concurrent.RecursiveAction;

public class ForkJoinParallelMatrixMultiplication extends RecursiveAction {
    private int[][] arr1;
    private int[][] arr2;
    private int[][] res;
    private int size;
    private int row;

    public ForkJoinParallelMatrixMultiplication(int[][] arr1,
                                                int[][] arr2,
                                                int size) {
        this(arr1, arr2, size, -1);
    }

    public ForkJoinParallelMatrixMultiplication(int[][] arr1,
                                                int[][] arr2,
                                                int size,
                                                int row) {
        this.arr1 = arr1;
        this.arr2 = arr2;
        this.size = size;
        this.res = new int[size][size];
        this.row = row;
    }

    @Override
    protected void compute() {
        if(this.row == -1){
            int rows = arr1.length;
            for(int i = 0; i < rows; i++) {
                invokeAll(new ForkJoinParallelMatrixMultiplication(arr1, arr2, size, i));
            }
        }
        else{
            multiplyRowByColumn(arr1, arr2, res, row);
        }
    }
    public void multiplyRowByColumn(int[][] arr1, int[][] arr2, int[][] res, int row){
        for(int j = 0; j < arr2[0].length; j++){
            for(int i = 0; i < arr1[0].length; i++){
                res[row][j] = res[row][j] + (arr1[row][i] * arr2[i][j]);
            }
        }
    }
}
