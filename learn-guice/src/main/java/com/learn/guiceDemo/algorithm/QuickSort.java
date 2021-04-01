package com.learn.guiceDemo.algorithm;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        int[]  data = {33,24,92,35,57,48,65,36,92,34};
       // initData(data);
        quickSort(data, 0, data.length-1);
        printData(data);
    }

    private static void quickSort(int[] data, int beginIndex, int lastIndex) {
        if(beginIndex >= lastIndex){
            return;
        }
        int next = process(beginIndex, lastIndex, data);
        quickSort(data, beginIndex, next-1);
        quickSort(data, next+1, lastIndex);

    }

    private static int  process(int beginIndex, int lastIndex, int[] data) {
        int tag = data[beginIndex];
        int left = beginIndex;
        int right = lastIndex;

        while(left < right){
            while (left < right && data[right] > tag){
                right --;
            }

            while(left < right && data[left] <= tag){
                left ++;
            }

            if(left < right){
                int tmp = data[left];
                data[left] = data[right];
                data[right] = tmp;
            }else {
                System.out.println(left + "<L---R>" + right);
            }
        }

        data[beginIndex] = data[left];
        data[left] = tag;

        return left;
    }

    private static void initData(int[] data) {
       for(int k =0 ; k< data.length;k++){
           Random random = new Random();
           data[k] = random.nextInt(100);
       }
        printData(data);
    }

    private static void printData(int[] data){
        System.out.println();
        for(int k =0 ; k< data.length;k++){
            System.out.print(data[k]);
            if(k!=data.length-1){
                System.out.print(",");
            }
        }
        System.out.println();
    }
}
