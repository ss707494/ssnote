package com.example.administrator.ssnote.util;

/**
 * Created by Administrator on 2015/9/26.
 * 处理时间间隔的方法
 */
public class HandleTimeUtil {
    private Long oneDay = 1000L * 60 * 60 * 24;
    private static int[] arr1 = { 1,  3,  4,  7, 11, 13, 17, 22, 27, 33};
    private static int[] arr2 = { 2,  5,  7, 10, 15, 18, 25, 30, 38, 44};
    private static int[] arr3 = { 4,  8, 12, 18, 20, 28, 34, 44, 57, 66};

    private static int[][] arrs = {arr1, arr2, arr3};

    public static Long handle( int state, int level, long time) {
        return time + arrs[state][level];
    }


}
