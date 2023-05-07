package com.luu;

import java.util.Scanner;

/**
 * ClassName: p41
 * Package: com.luu
 * Description:
 *
 * @Author Luu
 * @Create 2023/4/26 16:49
 * @Version 1.0
 */
//public class p41 {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int money = sc.nextInt();
//        if(money >= 600){
//            System.out.println("支付成功");
//        }else {
//            System.out.println("支付失败");
//        }
//    }
//}


public class p41 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ticket = sc.nextInt();
        if(ticket >= 0 && ticket <= 100){
            if(ticket % 2 == 1){
                System.out.println("left");
            }else {
                System.out.println("right");
            }
        }else {
            System.out.println("票号错误");
        }
    }
}