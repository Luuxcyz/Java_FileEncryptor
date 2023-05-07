package com.luu;
import java.util.Scanner;

/**
 * ClassName: p44
 * Package: com.luu
 * Description:
 *
 * @Author Luu
 * @Create 2023/4/26 17:24
 * @Version 1.0
 */
public class p44 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int week = sc.nextInt();
        switch (week){
            case 1,2,3,4,5 ->System.out.println("work");
            case 6,7 -> System.out.println("rest");
            default ->System.out.println("no");
        }
    }
}
