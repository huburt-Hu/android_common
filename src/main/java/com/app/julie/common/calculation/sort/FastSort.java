package com.app.julie.common.calculation.sort;

/**
 * Created by julie
 * <p>
 * Created on 2017/5/11.
 */

public class FastSort {

    //快速排序在序列中元素很少时，效率将比较低，不如插入排序，因此一般在序列中元素很少时使用插入排序，这样可以提高整体效率。
    public static void quick(int[] array, int lo, int hi) {
        if (hi - lo + 1 < 10) {
            insertSort(array);
        } else {
            quickSort(array, lo, hi);
        }
    }

    public static void quickSort(int[] a, int l, int h) {
        if (l > h) {
            return;
        }
        int index = partition(a, l, h);
        quickSort(a, 0, index - 1);
        quickSort(a, index + 1, h);
    }

    private static int partition(int[] a, int l, int h) {
        int tmp = getMiddle(a, l, h);
        while (l < h) {
            while (a[h] >= tmp && l < h) {
                h--;
            }
            a[l] = a[h];
            while (a[l] <= tmp && l < h) {
                l++;
            }
            a[h] = a[l];
        }
        a[l] = tmp;
        return l;
    }

    private static int getMiddle(int[] a, int l, int h) {
        int m = l + (h - l) / 2;
        if (a[m] > a[h]) {
            swap(a, m, h);
        }
        if (a[l] > a[h]) {
            swap(a, l, h);
        }
        if (a[l] < a[m]) {
            swap(a, l, m);
        }
        return a[l];
    }

    private static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }


    public static void insertSort(int[] array) {
        int j, total = array.length;
        for (int i = 1; i < total; i++) {
            int tmp = array[i];
            for (j = i; j > 0 && array[j - 1] > tmp; j--) {
                array[j] = array[j - 1];
            }
            array[j] = tmp;
        }
    }
}
