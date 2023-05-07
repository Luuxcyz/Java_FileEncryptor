package com.luu;

import java.util.Scanner;

/**
 * ClassName: p50
 * Package: com.luu
 * Description:
 *
 * @Author Luu
 * @Create 2023/5/5 21:28
 * @Version 1.0
 */

public class p50 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = sc.nextInt(); i <= 100 ; i++) {
            if (i % 10 == 7 || i / 10 % 10 == 7 || i % 7 == 0) {
                System.out.println("过");
                continue;
            }
            System.out.println(i);
        }
    }
}
