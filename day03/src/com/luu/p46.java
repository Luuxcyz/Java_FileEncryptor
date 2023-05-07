package com.luu;

import java.util.Scanner;

/**
 * ClassName: p46
 * Package: com.luu
 * Description:
 *
 * @Author Luu
 * @Create 2023/4/26 21:25
 * @Version 1.0
 */
//public class p46 {
//    public static void main(String[] args) {
//        int sum = 0;
//        for (int i = 1; i <= 100; i++) {
//            if(i % 2 == 0){
//                sum += i;
//            }
//        }
//        System.out.println(sum);
//    }
//}

public class p46 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入start");
        int start = sc.nextInt();
        System.out.println("输入end");
        int end = sc.nextInt();
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                num +=1;


            }
        }
        System.out.println(num);
    }}


