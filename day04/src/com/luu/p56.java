package com.luu;

/**
 * ClassName: p56
 * Package: com.luu
 * Description:
 *
 * @Author Luu
 * @Create 2023/5/6 19:01
 * @Version 1.0
 */
public class p56 {
    public static void main(String[] args) {
        int sum = 0;

        int[] arr = {1,2,3,4,5};
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        System.out.println(sum);
    }
}

