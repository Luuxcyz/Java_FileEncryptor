package com.luu;

import java.util.Scanner;

/**
 * ClassName: p64
 * Package: com.luu
 * Description:
 *
 * @Author Luu
 * @Create 2023/5/7 00:58
 * @Version 1.0
 */
public class p64 {
    public static void main(String[] args) {
//        playGame();
        grith();
    }
    public static void playGame(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入a:");
        int a = sc.nextInt();
        System.out.println("请输入b:");
        int b = sc.nextInt();
        int sum = a + b;
        System.out.println("最后结果为:" + sum);
        }
    public static void grith(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入长:");
        double l = sc.nextInt();
        System.out.println("请输入宽:");
        double w = sc.nextInt();
        double lw = (l + w) * 2;
        System.out.println("最后周长结果为:" + lw);
    }

}

