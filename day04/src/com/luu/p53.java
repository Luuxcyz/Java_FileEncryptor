package com.luu;

import java.util.Random;
import java.util.Scanner;

/**
 * ClassName: p53
 * Package: com.luu
 * Description:
 *
 * @Author Luu
 * @Create 2023/5/6 17:45
 * @Version 1.0
 */
public class p53 {
    public static void main(String[] args) {
        Random r = new Random();
        int number =  r.nextInt(100) + 1 ;
        Scanner sc = new Scanner(System.in);
        int count =1;
        for (int i = 0; i < 10; i++) {
            System.out.println("这是第"+count+"次请输入要猜的数字:");
            int number_g = sc.nextInt();
            if (count == 5) {
                System.out.println("保底奖励 猜中了");
                break;
            }
            count ++;
            if (number_g > number) {
                System.out.println("大了 小一些");
            } else if(number_g < number){
                System.out.println("小了 大一些");
            }else {
                System.out.println("恭喜你猜对了");
                break;
            }
        }
        sc.close();
    }
}
