package com.luu;

/**
 * ClassName: p47
 * Package: com.luu
 * Description:
 *
 * @Author Luu
 * @Create 2023/4/26 21:53
 * @Version 1.0
 */
//public class p47 {
//    public static void main(String[] args) {
//        int i =1;
//        while (i <= 100){
//            System.out.println(i);
//            i++;
//        }
//    }
//}

//public class p47 {
//    public static void main(String[] args) {
//        int count = 0;
//        double Mountain = 8844430;
//        double paper = 0.1;
//        double paper_h = paper;
//        while (paper_h<= Mountain){
//           paper_h *=2;
//           count++;
//        }
//        System.out.println("纸需要折叠" + count +"次");
//    }
//}

import java.math.BigDecimal;

public class p47 {
    public static void main(String[] args) {
        BigDecimal paperThickness = BigDecimal.valueOf(0.1); // 纸的厚度，单位是毫米
        BigDecimal mountHeight = BigDecimal.valueOf(8844430); // 珠穆朗玛峰的高度，单位是毫米

        int foldCount = 0;
        BigDecimal paperHeight = paperThickness;

        while (paperHeight.compareTo(mountHeight) < 0) {
            paperHeight = paperHeight.multiply(BigDecimal.valueOf(2));
            foldCount++;
        }

        System.out.println("纸需要折叠" + foldCount + "次才能达到珠穆朗玛峰的高度。");
    }
}

